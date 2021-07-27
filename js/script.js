const url = 'https://40dxits28f.execute-api.us-east-1.amazonaws.com/Alpha'
window.onload = () => {
	localStorage.clear()
	document.querySelector('#login').disabled = true
	document.querySelector('#createUser').disabled = true
	document.querySelector('#nameInput').setAttribute('onkeyup','disableLogin()')
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

const disableLogin = () => {
	if (document.querySelector('#nameInput').value.length == 0){ 
		document.querySelector('#login').disabled = true
		document.querySelector('#createUser').disabled = true
	}
	if (document.querySelector('#nameInput').value.length != 0){
		document.querySelector('#login').disabled = false
		document.querySelector('#createUser').disabled = false
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
			else {
				let oldName = document.querySelector('#nameInput').value
				let newName = oldName.charAt(0).toUpperCase() + oldName.slice(1).toLowerCase()
				localStorage.setItem('userName', newName);
				localStorage.setItem('idUser', json.user.idUser);
				window.location.href='trickPage.html';
				}
        })
}