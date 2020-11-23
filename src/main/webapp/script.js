function redrawCanvas() {
    let canvas = document.getElementById('graph')
    let ctx = canvas.getContext('2d')
    ctx.clearRect(0, 0, canvas.width, canvas.height)
    let valueR = document.querySelector('input[name = "R"]:checked')
    if (valueR !== null) {
        let r = Number.parseInt(valueR.value)
        ctx.fillStyle = '#EE00FFAA'
        ctx.strokeStyle = '#EE00FF'
        ctx.beginPath()
        ctx.moveTo(200, 200 - 24 *r)
        ctx.lineTo(200+24*r, 200)
        ctx.lineTo(200 + 24*r, 200 + 24*r)
        ctx.arcTo(200 - 24*r, 200 + 24*r, 200 - 24*r, 200, 24*r)
        ctx.lineTo(200, 200)
        ctx.lineTo(200, 200 - 24*r)
        ctx.fill()
        ctx.stroke()
    }

    ctx.strokeStyle = '#FFFFFF'
    {
    ctx.beginPath()
    ctx.moveTo(200, 50)
    ctx.lineTo(200, 350)
    ctx.moveTo(200, 50)
    ctx.stroke()

    ctx.beginPath()
    ctx.moveTo(195, 55)
    ctx.lineTo(200, 50)
    ctx.moveTo(205, 55)
    ctx.lineTo(200, 50)
    ctx.stroke()

    ctx.beginPath();
    let y = 80;
    while (y <= 320){
        ctx.moveTo(195, y)
        ctx.lineTo(205, y)
        y=y+12
    }
    ctx.moveTo(195, 80)
    ctx.stroke()

    ctx.beginPath()
    ctx.moveTo(50, 200)
    ctx.lineTo(350, 200)
    ctx.moveTo(50, 200)
    ctx.stroke()

    ctx.beginPath()
    ctx.moveTo(345, 195)
    ctx.lineTo(350, 200)
    ctx.moveTo(345, 205)
    ctx.lineTo(350, 200)
    ctx.stroke()

    ctx.beginPath();
    let x = 80;
    while (x <= 320){
        ctx.moveTo(x, 195)
        ctx.lineTo(x, 205)
        x=x+12
    }
    ctx.moveTo(80, 195)
    ctx.stroke()
}

    ctx.fillStyle = '#FFFFFF'
    {
        ctx.fillText("Y", 210, 50)
        ctx.fillText("R", 210, 80)
        ctx.fillText("R/2", 210, 140)
        ctx.fillText("-R/2", 210, 260)
        ctx.fillText("-R", 210, 320)

        ctx.fillText("-R", 80, 190)
        ctx.fillText("-R/2", 140, 190)
        ctx.fillText("R/2", 260, 190)
        ctx.fillText("-R/2", 320, 190)
        ctx.fillText("X", 350, 190)
    }

    if (valueR !== null){
        for (let dot of dots){
            if ("Invalid" === dot.hit){
                ctx.fillStyle = '#0000FF'
                ctx.fillRect((dot.x*24)+200, -(dot.y*24)+200, 3,3)
            }
            else if ("true" === dot.hit){
                ctx.fillStyle = '#00FF00'
                ctx.fillRect((dot.x*24)+200, -(dot.y*24)+200, 3,3)
            }
            else {
                ctx.fillStyle = '#FF0000'
                ctx.fillRect((dot.x*24)+200, -(dot.y*24)+200, 3,3)
            }

        }
    }
}

document.onreadystatechange = function () {
    document.getElementById('graph').addEventListener('mousedown', makeDot)
    document.getElementById("checkR_1").parentElement.addEventListener('change',redrawCanvas)
    redrawCanvas()
}

let dots = []

function validate(){
    let errors = document.getElementsByClassName("Error")
    for (i = 0; i < errors.length ; i++){
        errors[i].remove()
    }
    let valueX = document.getElementById("checkX")
    let valueR = document.querySelector('input[name = "R"]:checked')
    let valueY = document.getElementById("checkY")

    if (!isNaN(valueX.value.replace(",","."))){
        if (valueX.value < -5 || valueX.value > 3){
            showError(valueX.parentElement.parentElement,"Number must be in range -5 ... 3")
            return false
    }
        else {

            if (valueX.value.trim() === ""){
                showError(valueX.parentElement.parentElement,"X is missed.")
                return false
            }
            if (valueY.value === ""){
                showError(valueY.parentElement.parentElement,"Y is missed.")
                return false
            }
            if (valueR === null || valueR.value === undefined){
                showError(document.getElementById('checkR_1').parentElement.parentElement,"R is missed.")
                return false
            }
            sendForm(valueX.value.replace(",","."), valueY.value, valueR.value)
            return true
        }
}
    else {
        showError(valueX.parentElement.parentElement,"X is NaN !")
        return false
    }
}

function addRow( X, Y, R, res){
    {
        let tbody = document.getElementById('res')
        let row = document.createElement("tr");
        row.className = 'data'
        tbody.appendChild(row);
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");

        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        td1.innerHTML = Number.parseFloat(X).toFixed(2)
        td2.innerHTML = Number.parseFloat(Y).toFixed(2)
        td3.innerHTML = R

        if(res === "true"){
            td4.innerHTML = "Попадание";
        }
        else {
            td4.innerHTML = "Промах";
        }
    }
}

function showError(inputElem, text){
    var errorCase = document.createElement("tr")
    var errorCell = document.createElement("td")
    errorCell.innerHTML = text
    inputElem.insertAdjacentElement('beforebegin',errorCase)
    errorCase.appendChild(errorCell)
    errorCell.classList.add("Error")
    errorCase.classList.add("Error")
}

function sendForm(x, y, r){
    let data;
    dots = []
    data = "X=" + x + "&Y=" + y + "&R=" + r + "&RequestType=dot"
    $.ajax({
        url: "http://localhost:7400/lab2/controller",
        type: "GET",
        data: data,
        success: function (msg) {
            let table = $('.data')
            let i = 0;
            for (i; i < table.length; i++){
                table[i].remove()
            }
            let result = String(msg).split("/")
            for (let strings of result){
                let results = String(strings).split(";")
                addRow(results[0], results[1], results[2], results[3])
                dots.push({
                    x: results[0],
                    y: results[1],
                    hit: results[3]
                });
                if(results[3] === "Invalid"){
                    dots.push({
                        x: x,
                        y: y,
                        hit: results[3]
                    });
                }
            }
            redrawCanvas()
        }
    })
}

function clearTable(){
    dots = []
    let table = $('.data')
    let i = 0;
    for (i; i < table.length; i++){
        table[i].remove()
    }
    let data = "&RequestType=clear"
    $.ajax({
        url: "http://localhost:7400/lab2/controller",
        type: "GET",
        data: data
    });
    redrawCanvas()
}

function makeDot(event) {
    let errors = document.getElementsByClassName("Error")
    for (i = 0; i < errors.length ; i++){
        errors[i].remove()
    }
    let valueR = document.querySelector('input[name = "R"]:checked')
    if (valueR === null){
        showError(document.getElementById('graph'), "Enter R for dotting mode")
        return
    }
    let x = (event.offsetX-200) / 24;
    let y = -(event.offsetY-200) / 24;
    sendForm(x,y,valueR.value)
}