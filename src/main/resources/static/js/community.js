
/** 问题回复*/
function commnet_reply() {

var id = $('#commnet_id').val();
var content = $('#comment_content').val();
console.log(id);
console.log(content);

//
$.ajax({
    type:'post',
    url:"/comment",
    async:false,
    contentType:"application/json",
    data:JSON.stringify({
        "parentId":id,
        "content":content,
        "type":2
    }),
    dataType:"json",
    success:function (data) {

        if(data.code == 200){
            $('#comment_content').hide();
        }else {
            if (data.code == 2003){
                var b = confirm(data.message);
                if(b== true){
                    window.open("https://github.com/login/oauth/authorize?client_id=223876fd83b95e12ba7a\n" +
                        "                &redirect_uri=http://localhost:8081/callback&scope=user&state=1")
                    window.localStorage.setItem("closable",true);
                }
            }else{
                alert(data.message);
            }
        }
    },
    error:function () {
    }
})
    
}