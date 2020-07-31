/** 问题回复*/
function commnet_reply() {

    var id = $('#commnet_id').val();
    var content = $('#comment_contents').val();
    console.log(id);
    console.log(content);
    commentToTarget(id,2,content);
}

/***
 * 评论二级回复，点解图标折叠效果实现。
 */
function collapseCommetReplay(e) {
    var id  = e.getAttribute("data-id");
    var comments = $("#comment-"+id);
    // 获取二级菜单的状态
    var flag = e.getAttribute("data-collapse");

    if(!flag){
        var subCommentContainer = $("#comment-" + id);

        if (subCommentContainer.children().length != 1){
            // 未添加样式  展开二级菜单
            e.setAttribute("data-collapse", "in");
            comments.addClass("in"); // div 添加样式
            // 节点上添加样式
            e.classList.add("active");

        }else{
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {

                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));
                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);


                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 sub-comment",
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);

                });
                // 未添加样式  展开二级菜单
                e.setAttribute("data-collapse", "in");
                comments.addClass("in"); // div 添加样式
                // 节点上添加样式
                e.classList.add("active");

            });
        }


}else{
        // 删除样式  折叠二级菜单
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        // 节点上删除样式
        e.classList.remove("active");

    }

}


function comment(e) {
    var id = e.getAttribute("data-id");
    var inputComment = $("#input-"+id).val();
    console.log(inputComment);
    commentToTarget(id,1,inputComment)

}

function commentToTarget(targetId,type,content) {

    if (!content) {
        alert("评论内容不能为空！")
        return;
    }

    $.ajax({
        type: 'post',
        url: "/comment",
        async: false,
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        dataType: "json",
        success: function (data) {

            if (data.code == 200) {
                // $('#comment_content').hide();
                window.location.reload()

            } else {
                if (data.code == 2003) {
                    var b = confirm(data.message);
                    if (b == true) {
                        window.open("https://github.com/login/oauth/authorize?client_id=223876fd83b95e12ba7a\n" +
                            "                &redirect_uri=http://localhost:8081/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(data.message);
                }
            }
        },
        error: function () {
        }
    })

    
}

/**
 * 点击选择标签
 * @param value
 */
function selectTag(e) {
    var value = e.getAttribute("data-tag");
    console.log(value)
    //debugger;
    var startValue = $('#tag').val();
    if (startValue) {
        // 不为空的时候
        var strings = startValue.split(',');
        if (strings.indexOf(value) == -1) {
            // 如果标签中包含了  就不再添加到标签中
            $('#tag').val(startValue + ',' + value);

        }
    } else {
        $('#tag').val(value)
    }

}

