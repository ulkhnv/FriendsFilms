var buttons = document.querySelectorAll('.btn');
for (var i = 0; i < buttons.length; i++) {
    buttons[i].onclick = async function() {
        const formData = new FormData();
        var id = this.getAttribute('data-index');
        var rating = document.querySelector('#select' + id).value;
        formData.append("rating", rating);
        formData.append("film_id", id);
        return await fetch('/film/update', {
            method: 'POST',
            enctype: 'multipart/form-data',
            body: formData,
        }).then(
            setTimeout(function () {
                window.location.reload()
            }, 300)
        );
    };

}

