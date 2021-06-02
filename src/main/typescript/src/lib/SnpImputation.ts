
import {FormData, objectArrayToJsonStr} from "./util";

export class SnpImputation {


    performSubmitAction(formData: FormData[]): string {
        // let formData = $("#snp_submit").serializeArray();
        console.log(formData);

        const result: string = objectArrayToJsonStr(formData); 
        console.log(result);

        return result;
        // const jobIdObj = await initializeJobId({});
        // console.log(jobIdObj);
        // await ajaxHello();

    }


}






// // ----------



// function objectArrayToJson(objArray) {
//     let obj = {};

//     for (let i = 0; i < objArray.length; i++) {
//         obj[objArray[i].name] = objArray[i].value;
//     }

//     return JSON.stringify(obj);
// }

// function initializeJobId(request) {
//     console.log(request);
//     return new Promise((resolve, reject) => {
//         try {
//             $.ajax({
//                 url: "http://localhost:8082/wabi/SNP_imputation",
//                 type: "POST",
//                 contentType: "application/json",
//                 async: false,
//                 dataType: "json",
//                 error: function() {
//                     console.log("failed");
//                     reject();
//                 },
//                 success: function(data) {
//                     console.log("success");
//                     console.log(data);
//                     resolve(data);
//                 },
//                 timeout: 3000
//             });
//         }
//         catch (e) {
//             console.log(e);
//         }
//     });
// }


// function ajaxHello(request) {
//     console.log(request);
//     return new Promise((resolve, reject) => {
//         $.ajax({
//             url: "/webui/rest/hello",
//             type: "POST",
//             cache: false,
//             contentType: "application/json",
//             xhrFields: {
//                 withCredentials: true
//             },
//             error: function(error) {
//                 console.log("failed : ", error);
//                 reject();
//             },
//             success: function(data) {
//                 console.log("success");
//                 console.log(data);
//                 resolve(data);
//             },
//             timeout: 3000
//         });
//     });
// }



// function setJobId(request) {
//     console.log(request);
//     return new Promise((resolve, reject) => {
//         $.ajax({
//             url: "/webui/rest/SNP_imputation",
//             type: "POST",
//             cache: false,
//             contentType: "application/json",
//             xhrFields: {
//                 withCredentials: true
//             },
//             error: function(error) {
//                 console.log("failed : ", error);
//                 reject();
//             },
//             success: function(data) {
//                 console.log("success");
//                 console.log(data);
//                 resolve(data);
//             },
//             timeout: 3000
//         });
//     });
// }


