<#-- @ftlroot ".." -->
<#include "/partials/header.ftl">
    <h1 style="text-align: center">Добро пожаловать!</h1>
    <div style="
    text-align: center;
    font-family: Arial;
    font-size: 20px;
">С помощью этого приложения вы можете посмотреть статистику добавления и удаления друзей.</div>
    <div style="
text-align: center;
font-family: Arial;
font-size: 20px;
margin-top: 20px;
">Также вы можете сохранять состояние списка друзей и просматривать их в будущем!</div>
    <div style="
text-align: center;
font-family: Arial;
font-size: 20px;
margin-top: 20px;
">Просто зайдите через ВК!</div>
<div style="position:relative; margin-top: 20px;">
    <button style="
    position:absolute;    
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, 0%);" id="vk" class="waves-effect waves-light btn">ВОЙТИ</button>
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
        if (el.text()) {
        localStorage.setItem("token", el.text());
        document.cookie = 'token='+el.text()+'; token_expires=Fri, 3 Aug 2020 20:47:11 UTC; path=/';
        el.text('');
        window.location.href= "http://microservices.eastus.cloudapp.azure.com:9999/";
        }
    });

</script>