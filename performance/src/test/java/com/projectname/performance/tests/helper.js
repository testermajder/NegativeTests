//this whole script is used in other scripts so we can reuse common things
import http from "k6/http";
import { group, check } from "k6";
import { Counter, Rate } from "k6/metrics"

//collect errors rate while executing tests; request body, url and response body will be log in console
export let errorRate = new Rate("errors");
//collect size of received data on response - useful as check point that can tell us that something unusual happened between two runs
export let epDataReceived = new Counter("endpoint_data_received");

let today = new Date();
let year = today.getFullYear();
let month = today.getMonth();
let day = today.getDate();

function sizeOfHeader(header) {
    return Object.keys(header).reduce((sum, key) => sum + key.length + header[key].length, 0);
}

export function trackReceivedDataPerURL(res) {
    epDataReceived.add(((sizeOfHeader(res.headers) + res.body.length)/1024).toFixed(2), { url: res.url });
}

export function trackErrorRate(success, res) {
    if (!success) {
       console.log("request body: " + res.request.body);
       console.log("request url: " + res.request.url);
       console.log("response body: " + res.body);
    }
    errorRate.add(!success);
}

//variable that parse date to yyyy-mm-dd format that could be useful everywhere
export function todaysDateFormatted() {
    let todaysDate = new Date(year, month, day);
    return todaysDate.getFullYear() + "-" + ('0' + (todaysDate.getMonth() + 1)).slice(-2) + "-" + ('0' + todaysDate.getDate()).slice(-2);
}

//variable that parse date to yyyy-mm-dd format that could be useful everywhere, used to generate tomorrow date
export function tomorrowDateFormatted() {
    let tomorrowDate = new Date(year, month, day);
    tomorrowDate.setTime(tomorrowDate.getTime() + 1000 * 60 * 60 * 24);
    return tomorrowDate.getFullYear() + "-" + ('0' + (tomorrowDate.getMonth() + 1)).slice(-2) + "-" + ('0' + tomorrowDate.getDate()).slice(-2);
}

//variable that parse date to yyyy-mm-dd format that could be useful everywhere, used to generate date for fifth day in the future; multiplied by 5
export function fiveDaysLaterDateFormatted() {
    let fiveDaysLaterDate = new Date(year, month, day);
    fiveDaysLaterDate.setTime(fiveDaysLaterDate.getTime() + 1000 * 60 * 60 * 24 * 5);
    return fiveDaysLaterDate.getFullYear() + "-" + ('0' + (fiveDaysLaterDate.getMonth() + 1)).slice(-2) + "-" + ('0' + fiveDaysLaterDate.getDate()).slice(-2);
}