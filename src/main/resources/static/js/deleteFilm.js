var buttons = document.querySelectorAll('.delete');
for (var i = 0; i < buttons.length; i++) {
    buttons[i].onclick = async function() {
        var id = this.getAttribute('data-index');
        await fetch('/film/delete/'.concat(id), {
            method: 'delete',
        }).then(document.getElementById("container".concat(id)).innerHTML="")
    }
}
