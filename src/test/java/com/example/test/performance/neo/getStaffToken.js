import http from "k6/http";
import { check, fail, group } from "k6";
import { Rate } from "k6/metrics"
import {trackReceivedDataPerURL} from "../helper.js";

//read file that contain users body data needed for getting token; all data is stored in users list
var users = JSON.parse(open("perf_user.json"));
export let errorRate = new Rate("errors");

export default function(baseUrl) {
//grouping results by some name so you can filter and group data later in grafana. It's safer to use some name like in example
// because if you use path for grouping it can be wrong if it contains some params that are changing like date
	let res = group("getToken", function() {
	    //endpoint path
		let path = "api/token";
		var tokens = new Array();
		//loop for each user and get token
		users.forEach((user, index) => {
            let payload = JSON.stringify({grantType: `${user.grantType}`, username: `${user.username}`, password: `${user.password}`});
            let params =  {
                headers: { "Content-Type": "application/json" },
                timeout: 120000
            };

            let res = http.request("POST", baseUrl + path, payload, params);

            trackReceivedDataPerURL(res);

            let success = check(res, {
                "Fetching token data": (res) => res.status == 200,
            });

            //if status is not success than log error and abort tests since without token other tests shouldn't be triggered
            if (!success) {
                errorRate.add(!success);
                console.log(req.body);
                fail("Status code is not 200. Token is not fetched - aborting test...");
            }

            let jsonRes = JSON.parse(res.body);
            tokens.push(jsonRes.token.accessToken);
		 });

        //return tokens for all users
		return tokens;
	});
	return res;
};


