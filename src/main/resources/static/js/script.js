$(document).ready(function () {
    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
    });

});

$(function(){
    $(".chat").niceScroll();

    var chatmessages = [{
        name: 'Roboter',
        avatar: 'https://bootdey.com/img/Content/avatar/avatar2.png',
        text: 'Texto 1',

    },
    {
        name: 'Du',
        avatar: 'https://bootdey.com/img/Content/avatar/avatar1.png',
        text: 'Texto 2',

    }];

    let htmldiv = ``;
    jQuery.each( chatmessages, function( i, item ) {
        console.log(item);
        let position = item.username=='bot'? 'left': 'right';
        let ago = item.ago;
        htmldiv += `<div class="answer ${position}">
                        <div class="avatar">
                            <img src="${item.avatar}" alt="${item.name}">
                            <div class="status offline"></div>
                        </div>
                        <div class="name">${item.name}</div>
                        <div class="text">
                            ${item.text}
                        </div>
                        <div class="time">${$.format.date(new Date().getTime(), "HH:mm")}</div>
                    </div>`;
    });

    console.log(htmldiv);
    $( "div#chat-messages" ).html(htmldiv);
    $(".chat").niceScroll();
})