


// jQuery CSRF countermeasure.
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
});


$(function() {

    console.log("snp_submit.js is loaded.");

    $('#snp_submit').on("submit", function(event) {
        event.preventDefault();
        performSubmitAction();
    });
});


// ----------

async function performSubmitAction() {

    // Gather information
    const formData = $("#snp_submit").serializeArray();
    console.log(formData);

    console.log("Hello, thymeleaf");
    console.log(objectArrayToJson(formData));

    const uploadFileName = $('#plinkFile')[0].value;

    // Generate a JobId.
    const jobIdObj = await initializeJobId({});
    console.log(jobIdObj);


    // Upload a file.
    console.log(uploadFileName);
    await uploadFile(jobIdObj.jobId,  uploadFileName);

}


// https://stackoverflow.com/questions/5392344/sending-multipart-formdata-with-jquery-ajax
function uploadFile(jobId, fileName) {
    //console.log(request);
    let data = new FormData();
    data.append('file', fileName);
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "http://localhost:8082/wabi/SNP_imputation/" + jobId + "/file_upload",
            type: "POST",
            method: "POST",
            data: data,
            cache: false,
            contentType: false,
            processData: false,
            // xhrFields: {
            //     withCredentials: true
            // },
            error: function(error) {
                console.log("failed : ", error);
                reject();
            },
            success: function(data) {
                console.log("success");
                console.log(data);
                resolve(data);
            },
            timeout: 3000
        });
    });
}



// ----------


function createObjectPathInstance(options) {
    const config = {
        seperator: '.',
        defaultUnsetValue: undefined,
        autoCreate: false
    };
    const configure = (options1 = {
    })=>{
        for(let k in config){
            if (options1.hasOwnProperty(k)) {
                config[k] = options1[k];
            }
        }
        return config;
    };
    const parse = (path)=>{
        return path.split(config.seperator);
    };
    const set = (obj, path, value)=>{
        const p = parse(path);
        if (p.length) {
            let o = obj;
            for(let i = 0; i < p.length - 1; i++){
                if (o !== null && typeof o === 'object') {
                    if (!o.hasOwnProperty(p[i])) {
                        if (config.autoCreate) {
                            o[p[i]] = {
                            };
                            o = o[p[i]];
                            continue;
                        } else {
                            return false;
                        }
                    } else {
                        o = o[p[i]];
                    }
                } else {
                    return false;
                }
            }
            o[p[p.length - 1]] = value;
            return true;
        }
        return false;
    };
    const get = (obj, path)=>{
        const p = parse(path);
        let v = obj;
        for(let i = 0; i < p.length; i++){
            if (v !== null && typeof v === 'object' && v.hasOwnProperty(p[i])) {
                v = v[p[i]];
            } else {
                return config.defaultUnsetValue;
            }
        }
        return v;
    };
    const module = {
        configure,
        parse,
        set,
        get
    };
    if (options) {
        configure(options);
    }
    return module;
}


function objectArrayToJson(info) {
    const { set  } = createObjectPathInstance({
        seperator: '.',
        defaultUnsetValue: undefined,
        autoCreate: true
    });
    const result = {
    };
    for(let i = 0; i < info.length; i++){
        set(result, info[i].name, info[i].value);
    }
    return JSON.stringify(result);
}



function initializeJobId(request) {
    console.log(request);
    return new Promise((resolve, reject) => {
        try {
            $.ajax({
                url: "http://localhost:8082/wabi/SNP_imputation",
                type: "POST",
                contentType: "application/json",
                async: false,
                dataType: "json",
                error: function() {
                    console.log("failed");
                    reject();
                },
                success: function(data) {
                    console.log("success");
                    console.log(data);
                    resolve(data);
                },
                timeout: 3000
            });
        }
        catch (e) {
            console.log(e);
        }
    });
}


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


