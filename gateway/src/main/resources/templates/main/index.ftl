<#-- @ftlroot ".." -->
<#include "/partials/header.ftl">
 <div class="row">
     <button id="vk" class="waves-effect waves-light btn">Vk</button>
 </div>

<script>

    $("#vk").click(function () {
        var name = location.origin;
        console.log(name);
        location = "https://oauth.vk.com/authorize" +
                "?client_id=6725739" +
                "&display=page" +
                "&redirect_uri=" + name + "/uaa/user/login/vk" +
                "&scope=friends,email,offline" +
                "&response_type=code" +
                "&v=5.87"
    });
</script>
<#include "/partials/footer.ftl">