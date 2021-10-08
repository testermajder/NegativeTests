import http from "k6/http";
import { group, check } from "k6";
import {trackReceivedDataPerURL, trackErrorRate} from "../helper.js";

export default function(data) {
  //call endpoint for each token
  data.staffToken.forEach((token, index) => {
    group("getSettings", function() {
      let path = "api/settings";
      let params = {
        headers: {
          Accept: "application/json, text/plain, */*",
          Authorization: "Bearer " + token
        },
        timeout: 120000
      };

      let res = http.request("GET", data.baseUrl + path, JSON.stringify(), params);

      let success = check(res, {
        "Fetching settings 2.0 data": (r) => r.status == 200
      });

      trackReceivedDataPerURL(res);
      trackErrorRate(success, res);
    });
  });
}