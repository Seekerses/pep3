let dots = [[],[],[],[],[]]
let r
let isCanvasCreated = false
let graph = document.createElement('canvas')

document.onreadystatechange = function () {
    if (isCanvasCreated){
        graph.id = "graph"
        graph.setAttribute('height', '400')
        graph.setAttribute('width', '400')
        document.getElementsByClassName('graph-container')[0].appendChild(graph)
        graph.addEventListener('mousedown', makeDot)
        loadDots()
        redrawCanvas()
        document.getElementById("r-value").addEventListener('click',redrawCanvas)
        let observer = new MutationObserver(function changer(mutations) {
                loadDots()
                redrawCanvas()
        })
        observer.observe(document.getElementsByTagName('body')[0], {
            childList: true,
            subtree: true
        })
        document.getElementById("makeDotForm:clear-button").addEventListener('click', event => {
            loadDots();
            redrawCanvas();
        })
    }
    isCanvasCreated = true
}

function setR(dec) {
    r = dec
}

function redrawCanvas() {
    let ctx = graph.getContext('2d')
    ctx.clearRect(0, 0, graph.width, graph.height)
    if (r !== undefined) {
        ctx.fillStyle = '#EE00FFAA'
        ctx.strokeStyle = '#FFFFFF'
        ctx.beginPath()
        ctx.moveTo(200, 200 - 24 *r)
        ctx.arcTo(200 + 24*r , 200 - 24*r, 200 + 24*r, 200, 24*r)
        ctx.lineTo(200 + 24*r/2, 200)
        ctx.lineTo(200, 200 + 24*r/2)
        ctx.lineTo(200, 200)
        ctx.lineTo(200 - 24*r, 200)
        ctx.lineTo(200 - 24*r, 200 - 24*r/2)
        ctx.lineTo(200, 200 - 24*r/2)
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
    if (r !== undefined){
        for (let dot of dots[r*2 -2]){
            if ("true" === dot.hit){
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

function loadDots(){
    dots = [[],[],[],[],[]]
    let tbody = document.getElementById('contentTable').getElementsByTagName('tbody')[0]
    if (tbody.childNodes.length > 2) {
        for (let i = 1; i < tbody.childNodes.length; i = i + 2) {
            let dot = {
                x: tbody.childNodes[i].childNodes[1].innerHTML,
                y: tbody.childNodes[i].childNodes[3].innerHTML,
                r: tbody.childNodes[i].childNodes[5].innerHTML,
                hit: tbody.childNodes[i].childNodes[7].innerHTML.trim() === "Hit" ? "true" : "false",
            }
            dots[Number.parseFloat(tbody.childNodes[i].childNodes[5].innerHTML) * 2 - 2].push(dot)
        }
    }
}

function showError(inputElem, text){
    var errorCase = document.createElement("div")
    var errorCell = document.createElement("span")
    errorCell.innerHTML = text
    inputElem.insertAdjacentElement('beforebegin',errorCase)
    errorCase.appendChild(errorCell)
    errorCell.classList.add("Error")
    errorCase.classList.add("Error")
}

function makeDot(event) {
    let errors = document.getElementsByClassName("Error")
    for (i = 0; i < errors.length ; i++){
        errors[i].remove()
    }
    if (r === undefined){
        showError(document.getElementById('graph'), "Enter R for dotting mode")
        return
    }
    let x = (event.offsetX-200) / 24;
    let y = -(event.offsetY-200) / 24;
    if ( x > 3 || x < -5){
        showError(graph,"Invalid X parameter of a dot")
        return;
    }
    if ( y > 3 || y < -5){
        showError(graph,"Invalid Y parameter of a dot")
        return;
    }
    document.getElementById('hidden-form:hidden-x').setAttribute('value', x)
    document.getElementById('hidden-form:hidden-x').dispatchEvent(new Event('change'))
    document.getElementById('hidden-form:hidden-y').setAttribute('value', y)
    document.getElementById('hidden-form:hidden-y').dispatchEvent(new Event('change'))
    document.getElementById('hidden-form:hidden-hit').click()
}

function validate(){
    let errors = document.getElementsByClassName("Error")
    for (i = 0; i < errors.length ; i++){
        errors[i].remove()
    }
    let x = document.getElementById('makeDotForm:x-span')
    let y = document.getElementById('makeDotForm:y-coordinate')
    let r = document.getElementById('makeDotForm:r-span')
    if (x.innerHTML.trim() === "") {showError(x.parentNode,"X value required") ; return}
    if (y.value.trim() === "") {showError(y.parentNode,"Y value required") ; return}
    if (r.innerHTML.trim() === "") {showError(r.parentNode,"R value required") ; return}

    if (isNaN(y.value.trim())) {showError(y.parentNode,"Y is not a number") ; return}

    if (Number.parseFloat(y.value) < -5 || Number.parseFloat(y.value) > 3) {showError(y.parentNode,"Y must be in range -3..5") ; return}

    document.getElementById('makeDotForm:add-button').click()
}