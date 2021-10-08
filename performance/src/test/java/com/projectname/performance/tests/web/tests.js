//main script that calls all other scripts and tests; those should be imported as shown below
import getStaffToken from "./getStaffToken.js";
import getSettings from "./getSettings.js";
//you can also import functions from other script and reuse
import {todaysDateFormatted, tomorrowDateFormatted, fiveDaysLaterDateFormatted} from "../helper.js";

//if you want to run tests on different environments and want to control it for example from jenkins
let base = `https://${__ENV.BASE_URL}/`;

//preparation for tests; all those properties will be stored in data object and can be used in main function - export default function
export function setup() {
	return {
	 baseUrl : base,
	 //get tokens for all users
	 staffToken : getStaffToken(base),
	 todaysDateFormatted : todaysDateFormatted(),
	 tomorrowDateFormatted : tomorrowDateFormatted(),
	 fiveDaysLaterDateFormatted : fiveDaysLaterDateFormatted()
	 }
}

//K6 options preparation
export let options = {
  setupTimeout: '60s',
  vus: 1,
  iterations: 5,
  //you can use tags to group complete run per some parameter; for example if you want to run it on different environments or
  // need to run with different setup this is going to help to filter runs in grafana
  tags: {
    source: "source1"
  }
}

//main function that should call all scripts and run all tests
export default function(data) {
    //call one of test scripts, organized per endpoing
    getSettings(data);
    //getToken should stay last test, since it fetches new token
    getStaffToken(data.baseUrl);
}

//here you can delete or clean some data if needed after all tests are run
export function teardown(data) {
}