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
			else document.querySelector('#nameInput').innerText = "You created a new user! "
        })
	}
}