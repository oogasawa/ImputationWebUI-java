
// jQuery CSRF countermeasure.
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
});


$(function() {

    // console.log("snp_imputation.js is loaded.");

    $(".deleteJobButton").on('click', async function(event) {
        console.log("clicked!");
        let row = $(this).closest("tr");
        let tds = row.find("td");

        let jobId = tds[0].textContent;
        console.log(jobId);
        await deleteJob(jobId);

        // location.reload(true);
        window.open(window.location.pathname, "_self");
        return true;
    });

    // console.log("am I here?");

});



async function deleteJob(jobId) {
    let result;
    try {
        result = await $.ajax({
            url: "/rest/SNP_imputation/" + jobId,
            type: "DELETE",
            contentType: "application/json",
            async: false,
            dataType: "json",
        });
        return result;
    }
    catch (e) {
        console.log(e);
    }
}


