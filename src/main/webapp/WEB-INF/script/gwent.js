const MYHANDDIVWIDTH = 1000;
const MYHANDMAXCARDS = 10;


var myHand = new Vue({
    el: '#my-hand',
    data: {
        // здесь хранятся только картинки, сам объект строится в computed и именно он используется в v-for
        cards: [
            {id: 0, url: 'img/1.png', bias: 0, isChoosed: false},
            {id: 1, url: 'img/2.png', bias: 0, isChoosed: false},
            {id: 2, url: 'img/3.png', bias: 0, isChoosed: false}
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
            {id: 0, url: 'img/back.png', bias: 0, isChoosed: false},
            {id: 1, url: 'img/back.png', bias: 0, isChoosed: false},
            {id: 2, url: 'img/back.png', bias: 0, isChoosed: false}
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