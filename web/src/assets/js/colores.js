function aColor(rojo, verde, azul) {
    const colorRGB = `rgb(${rojo}, ${verde}, ${azul})`;
    document.body.style.backgroundColor = colorRGB;
}

function actualizar(){
    var r = document.getElementById("rojo").value;
    var v = document.getElementById("verde").value;
    var a = document.getElementById("azul").value;

    document.getElementById('txtrojo').value = r;
    document.getElementById('txtverde').value = v;
    document.getElementById('txtazul').value = a;
    aColor(r, v, a);
}
actualizar();