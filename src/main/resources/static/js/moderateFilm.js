var buttons = document.querySelectorAll('.moderate');
for (var i = 0; i < buttons.length; i++) {
    buttons[i].onclick = async function() {
        var id = this.getAttribute('data-index');
         await fetch('/film/save/'.concat(id), {
            method: 'POST',
        }).then(document.getElementById("container".concat(id)).innerHTML="")
    }
}

