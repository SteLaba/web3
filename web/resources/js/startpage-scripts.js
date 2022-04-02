function showTimeAndDate(){
    let date = new Date();
    let month = date.getMonth();
    let day = date.getDate();
    let hours = date.getHours()
    let minutes = date.getMinutes()
    let seconds = date.getSeconds()

    hours = (hours < 10) ? "0" + hours : hours
    minutes = (minutes < 10) ? "0" + minutes : minutes
    seconds = (seconds < 10) ? "0" + seconds : seconds
    month = (month < 10) ? "0" + month : month
    day = (day < 10) ? "0" + day : day

    document.getElementById("start-clock").textContent = hours + ":" + minutes + ":" + seconds + "  " + day + "/" + month

    setTimeout(showTimeAndDate, 6000);
}