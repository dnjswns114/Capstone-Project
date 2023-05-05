

//오늘 날짜출력
$(document).ready(function () {

    function convertTime() {
        var now = new Date();


        var month = now.getMonth() + 1;
        var date = now.getDate();

        return month + '월' + date + '일';
    }

    var currentTime = convertTime();
    $('.nowtime').append(currentTime);
});


//제이쿼리사용
$.getJSON('https://api.openweathermap.org/data/2.5/weather?q=Osan&appid=9873fbac875f44a531651c5883ad7546&units=metric',
    function (WeatherResult) {
        //기온출력
        $('.OsanNowtemp').append(Math.floor(WeatherResult.main.temp));
        $('.OsanLowtemp').append(Math.floor(WeatherResult.main.temp_min));
        $('.OsanHightemp').append(Math.floor(WeatherResult.main.temp_max));

        //날씨아이콘출력
       //WeatherResult.weater[0].icon
        var weathericonUrl =
            '<img src= "http://openweathermap.org/img/wn/'
            + WeatherResult.weather[0].icon +
            '.png" alt="' + WeatherResult.weather[0].description + '"/>'

        $('.OsanIcon').html(weathericonUrl);
    });