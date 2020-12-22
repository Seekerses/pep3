function clock() {
    let hoursText = document.getElementById('hours')
    let minText = document.getElementById('min')
    let secText = document.getElementById('sec')

    let date = new Date()

    hoursText.innerHTML = date.getHours() < 10 ? "0" + date.getHours().toString() : date.getHours().toString()
    minText.innerHTML = date.getMinutes() < 10 ? "0" + date.getMinutes().toString() : date.getMinutes().toString()
    secText.innerHTML = date.getSeconds() < 10 ? "0" + date.getSeconds().toString() : date.getSeconds().toString()
}

clock()
setInterval(clock, 11000)
