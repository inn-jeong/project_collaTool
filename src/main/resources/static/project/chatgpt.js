let bCategory = $("#bCategory").val();
let projectId = $("#projectId").val();
$(document).ready(function(){
    $("#btn-rewrite").on('click',function(){
        $("#textArea").text('작성중..');
        $("#btn-rewrite").attr("hidden",true);
        $.ajax({
            url:"/chatgpt/delete",
            type:"POST",
            data:{
                projectId:projectId
            },
            success:function(response){
                insertContent();
            },
            error:function(e){
                alert(e);
            }
        });
    });

    console.log("prompt"+bCategory+" id:"+projectId);
    $.ajax({
        url:"/chatgpt/check",
        type:"POST",
        data:{
            projectId:projectId
        },
        success:function(response){
            if(response == "is null"){
                insertContent();
            }else{
                console.log(response);
                $("#textArea").html(response);
                $("#btn-rewrite").removeAttr("hidden");
            }

        },
        error:function(e){
            alert(e);
        }

    });
});
function insertContent(){
    $.ajax({
        url:"/chatgpt/chat",
        type:"POST",
        data:{
            prompt:bCategory,
            projectId:projectId
        },
        success:function(response){
            console.log(response);
            $("#textArea").html(response.choices[0].message.content);
            $("#btn-rewrite").removeAttr("hidden");
        },
        error:function(e){
            $("#textArea").text("오류 발생. 다시 시도해주세요.")
            $("#btn-rewrite").removeAttr("hidden");
        }
    });
}