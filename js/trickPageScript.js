const url = 'https://40dxits28f.execute-api.us-east-1.amazonaws.com/Alpha'
window.onload = () => {
	document.querySelector('#welcome').innerText = "Welcome, " + localStorage.getItem('userName');
	
	fetch(url+'/getAllTricks', {
        method:'POST',
        body:JSON.stringify({idUser:localStorage.getItem('idUser')})
    })
    .then( response => response.json())
    .then(json=> {
        console.log(json)
		if(json.statusCode==400) alert("Error: Unable to load tricks")
		else {//Tricks loaded
			const trickDiv = document.getElementById('listOfTricksDiv')
			//clear tricks
			while(trickDiv.firstChild){
				trickDiv.removeChild(trickDiv.firstChild)
			}
			const maxCards = json.tricks.length
			var count = 0
			
			var row = document.createElement('div')
			row.setAttribute('class','card-deck text-dark')
			while(count != maxCards){
				const card = document.createElement('div')
				card.setAttribute('class','card')
				card.setAttribute('style','width: 18rem;')
				const cardBody = document.createElement('div')
				cardBody.setAttribute('class','card-body')
				const cardTitle = document.createElement('h5')
				cardTitle.setAttribute('class','card-title')
				cardTitle.innerText = json.tricks[count].trickName
				cardBody.appendChild(cardTitle)
				const cardText = document.createElement('p')
				cardText.setAttribute('class','card-text')
				cardText.innerText = json.tricks[count].trickDes
				cardBody.appendChild(cardText)
				card.appendChild(cardBody)
				row.appendChild(card)
				count++
				if(count%6 == 0){
					trickDiv.appendChild(row)
					trickDiv.appendChild(document.createElement('br'))
					row = document.createElement('div')
					row.setAttribute('class','card-deck text-dark')
				}
				
			}
			if (count%6 != 0){
				trickDiv.appendChild(row)
				trickDiv.appendChild(document.createElement('br'))
			}
		}
    })
	
}