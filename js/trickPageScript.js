const url = 'https://40dxits28f.execute-api.us-east-1.amazonaws.com/Alpha'
window.onload = () => {
	
	updateTricks()
	updateCombos()
	
	document.querySelector('#welcome').innerText = "Welcome, " + localStorage.getItem('userName');
	
	document.querySelector('#addButton').onclick = e=> {
		fetch(url+'/addCustomTrick', {
            method:'POST',
            body:JSON.stringify({trickName:document.querySelector('#trickNameArea').value, trickDes:document.querySelector('#trickDescriptionArea').value, customUser:localStorage.getItem('idUser')})
        })
        .then( response => response.json())
        .then(json=> {
            console.log(json)
			if(json.statusCode==400) alert("Error")
			else {
				//loading gif just in case
				const trickDiv = document.getElementById('listOfTricksDiv')
				//clear tricks
				while(trickDiv.firstChild){
					trickDiv.removeChild(trickDiv.firstChild)
				}
				const loading = document.createElement('img')
				loading.setAttribute('src','/img/loading.gif')
				loading.setAttribute('alt','Loading')
				trickDiv.appendChild(loading)
				
				updateTricks()
			}
        })
	}
	
}

const updateTricks = () => {
		fetch(url+'/getAllTricks', {
        method:'POST',
        body:JSON.stringify({idUser:localStorage.getItem('idUser')})
    })
    .then( response => response.json())
    .then(json=> {
        console.log(json)
		if(json.statusCode==400) alert("Error: Unable to load tricks")
		else {//Tricks loaded
			localStorage.setItem('tricks', JSON.stringify(json.tricks))
			
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
				const viewButton = document.createElement('button')
				viewButton.setAttribute('type', 'button')
				viewButton.setAttribute('class', 'btn btn-secondary viewButtons')
				viewButton.setAttribute('data-toggle', 'modal')
				viewButton.setAttribute('data-target', '#trickViewModal')
				viewButton.setAttribute('data-idTrick', json.tricks[count].idTrick)
				viewButton.innerText = "View"
				cardBody.appendChild(viewButton)
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
			//set up buttons
			var buttons = document.querySelectorAll('.viewButtons')
			for (let butt of buttons) {
			  butt.onclick = e=> {
					const trick = getTrickFromID(butt.dataset.idtrick)
					//set title and descriptions
					document.querySelector('#trickViewModalLabel').innerText = trick.trickName
					document.querySelector('#trickDesBody').innerText = trick.trickDes
					setCombosInView(trick)			
				}
			}
		}
    })
}

const setCombosInView = (trick) => {
	//set combos
	const comboDiv = document.querySelector('#comboDiv')
	//clear comboDiv
	comboDiv.innerText = ""
	while(comboDiv.firstChild){
		comboDiv.removeChild(comboDiv.firstChild)
	}
	const comboList = getComboIntos(trick.idTrick)
	for (var i = 0; i < comboList.length; i++) {
		const comboButton = document.createElement('button')
		comboButton.setAttribute('type',"button")
		comboButton.setAttribute('class',"btn badge badge-primary comboButtons")
		comboButton.innerText = getTrickFromID(comboList[i].comboInto).trickName + " x"
		comboButton.setAttribute('style','margin-left: 5px;margin-right: 5px;')
		comboDiv.appendChild(comboButton)
	}
}

const getComboIntos = (id) => {
	const list = JSON.parse(localStorage.getItem('combos'))
	const queryList = []
	for (var i = 0; i < list.length; i++) {
		if (list[i].comboFrom == id) queryList.push(list[i])
	}
	return queryList
}

const updateCombos = () => {
	fetch(url+'/getListOfCombos ', {
            method:'GET',
        })
        .then( response => response.json())
        .then(json=> {
            console.log(json)
			if(json.statusCode==400) alert("Error: getting combos failed")
			else {
				localStorage.setItem('combos', JSON.stringify(json.combos))
			}
        })
}

const getTrickFromID = (id) => {
	const list = JSON.parse(localStorage.getItem('tricks'))
	for (var i = 0; i < list.length; i++) {
		if (id == list[i].idTrick){
			return list[i]
		}
	}
	console.log(localStorage.getItem('tricks'))
	return null
}

