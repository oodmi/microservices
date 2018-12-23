<#-- @ftlroot ".." -->
<#include "/partials/header.ftl">
 <div class="row">
     <button id="vk" class="waves-effect waves-light btn">Vk</button>
     <div id="token" hidden>${token}</div>
 </div>

<script>

    $("#vk").click(function () {
        var name = location.origin;
        console.log(name);
        location = "https://oauth.vk.com/authorize" +
                "?client_id=6725739" +
                "&display=page" +
                "&redirect_uri=" + name +
                "&scope=friends,history,email,offline" +
                "&response_type=code" +
                "&v=5.87"
    });
    $(function () {
        var el = $("#token");
        console.log(el.text());
        localStorage.setItem("token", el.text());
        el.text('');
    });

</script>
<#include "/partials/footer.ftl">