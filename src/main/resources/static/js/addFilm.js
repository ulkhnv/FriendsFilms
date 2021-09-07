document.getElementById("addFilm").onclick = async function () {
    let container = document.getElementById("alertContainer")
    let filmName = document.getElementById("filmName").value
    document.getElementById("filmName").value=""
    return await fetch('/film/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: filmName
    }).then(
        container.append('Фильм успешно отправлен на модерацию'),
        setTimeout(function () {
            container.innerHTML = "";
        }, 2000)
    );

}