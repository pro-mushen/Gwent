const MYHANDDIVWIDTH = 1000;
const MYHANDMAXCARDS = 10;


var loginForm = new Vue({

    el: '#login-wrapper',
    data: {
        isOpened: false
    },
    created: function () {
        /*
            1 - check for authorization
            2 - if no authorized - add to wrapper class "show"
            3 - if authorized - do nothing
        */
        console.log('created');

        var answer = getLoginStatus();
        console.log(answer);

        setTimeout(function () {
            console.log('time over');
            loginForm.isOpened = true;
        }, 2000);
    }
});


var myHand = new Vue({
    el: '#my-hand',
    data: {
        // здесь хранятся только картинки, сам объект строится в computed и именно он используется в v-for
        cards: [
            {id: 0, url: 'resources/img/cards/1.png', bias: 0, isChoosed: false},
            {id: 1, url: 'resources/img/cards/2.png', bias: 0, isChoosed: false},
            {id: 2, url: 'resources/img/cards/3.png', bias: 0, isChoosed: false}
        ],
        whatChoosed: undefined
    },
    watch: {
        cards: function (newCards, oldCards) {

            console.log('watchdog!');

            for (var i = 0; i < newCards.length; i++) {

                // вычислим на сколько сдвинуть карты влево, если их много
                var bias;
                if (i === 0 || newCards.length <= MYHANDMAXCARDS) { // если это первая карта или карт меньше 7, то всем 0
                    bias = 0
                } else {   // если это не первая карта и карт больше 7, то со 2-й вычисляем
                    bias = Math.floor(MYHANDDIVWIDTH / (newCards.length) - MYHANDDIVWIDTH / MYHANDMAXCARDS);
                }

                oldCards[i].bias = bias + 'px';
            }
        }
    },

    methods: {
        choose: function (newId) {
            console.log(newId, this.whatChoosed);

            if (this.whatChoosed !== undefined) {
                this.cards[this.whatChoosed].isChoosed = false
            }

            this.cards[newId].isChoosed = true;
            this.whatChoosed = newId;
        }
    }

});



var oppHand = new Vue({
    el: '#opp-hand',
    data: {
        // здесь хранятся только картинки, сам объект строится в computed и именно он используется в v-for
        cards: [
            {id: 0, url: 'resources/img/cards/back.png', bias: 0, isChoosed: false},
            {id: 1, url: 'resources/img/cards/back.png', bias: 0, isChoosed: false},
            {id: 2, url: 'resources/img/cards/back.png', bias: 0, isChoosed: false}
        ],
        whatChoosed: undefined
    },
    watch: {
        // следим за изменением состава массива cards
        cards: function (newCards, oldCards) {

            for (var i = 0; i < newCards.length; i++) {

                // вычислим на сколько сдвинуть карты влево, если их много
                var bias;
                if (i === 0 || newCards.length <= MYHANDMAXCARDS) { // если это первая карта или карт меньше 7, то всем 0
                    bias = 0
                } else {   // если это не первая карта и карт больше 7, то со 2-й вычисляем
                    bias = Math.floor(MYHANDDIVWIDTH / (newCards.length) - MYHANDDIVWIDTH / MYHANDMAXCARDS);
                }

                oldCards[i].bias = bias + 'px';
            }
        }
    },

    methods: {
        choose: function (newId) {
            console.log(newId, this.whatChoosed);

            if (this.whatChoosed !== undefined) {
                this.cards[this.whatChoosed].isChoosed = false
            }

            this.cards[newId].isChoosed = true;
            this.whatChoosed = newId;
        }
    }

});


// AJAX


function getLoginStatus() {
    // (1) создать объект для запроса к серверу
    var req = new XMLHttpRequest();

    // (2)


    req.onreadystatechange = function () {
        // onreadystatechange активируется при получении ответа сервера

        console.log(req);

        if (req.readyState === 4) {
            // если запрос закончил выполняться

            //statusElem.innerHTML = req.statusText; // показать статус (Not Found, ОК..)
            console.log(req.statusText);

            if (req.status === 200) {
                // если статус 200 (ОК) - выдать ответ пользователю
                console.log("Ответ сервера: " + req.responseText);
            } else {
                console.log(req.status);
            }
        }

    };


    // (3) задать адрес подключения
    req.open('POST', '/authorization', true);

    // объект запроса подготовлен: указан адрес и создана функция onreadystatechange
    // для обработки ответа сервера

    // (4)
    req.setRequestHeader('Content-Type', 'text/plain');
    req.send();  // отослать запрос

    // (5)
    //statusElem.innerHTML = 'Ожидаю ответа сервера...'

    console.log('Ожидаю ответа сервера...');

}




/*
$(window).ready(function () {
    var a = {
        top: 750,
        left: 400
    };

    // переместить карту в нужное место
    $('#my-hand').offset(a);
});
*/