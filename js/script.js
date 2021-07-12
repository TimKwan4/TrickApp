const url = 'https://40dxits28f.execute-api.us-east-1.amazonaws.com/Alpha'
window.onload = () => {
	localStorage.clear()
	document.querySelector('#createUser').onclick = e=> {

		fetch(url+'/createNewUser', {
            method:'POST',
            body:JSON.stringify({name:document.querySelector('#nameInput').value})
        })
        .then( response => response.json())
        .then(json=> {
            console.log(json)
			if(json.statusCode==400) alert("Error: Please Try Another Name")
			else {login()}
        })
	}
	document.querySelector('#login').onclick = e=> {
		login()
	}
}

const login = () => {
    fetch(url+'/login', {
            method:'POST',
            body:JSON.stringify({name:document.querySelector('#nameInput').value})
        })
        .then( response => response.json())
        .then(json=> {
            console.log(json)
			if(json.statusCode==400) alert("Error: Name Doesn't Exist")
			else {localStorage.setItem('userName', document.querySelector('#nameInput').value);localStorage.setItem('idUser', json.user.idUser);window.location.href='trickPage.html';}
        })
}