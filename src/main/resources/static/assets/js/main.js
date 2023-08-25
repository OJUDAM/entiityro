$(function() {

    $('#convertBtn').on("click",function () {
        setArrivalTime();
    });

});


function setArrivalTime(){
    const tableScript = $("#tableScript").val();

    $.ajax({
        contentType: "application/text; charset=utf-8",
        type : 'get',
        url:'/convert',
        //url : 'http://localhost:8080/convert',
        data : {createScript: tableScript},
        dataType : 'text',
        success: function(data, textStatus) {
            if(data !== 'null') {
                $("#entityScript").val(data);
            }
        },
        error: function(xhr, status, error){
            console.log(error);
        }
    });
}


