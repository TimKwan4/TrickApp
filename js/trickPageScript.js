const url = 'https://40dxits28f.execute-api.us-east-1.amazonaws.com/Alpha'
window.onload = () => {
	
	updateTricks()
	updateCombos()
	updateStatus()
	document.querySelector('#comboIntoSelect').setAttribute('onclick','disableAddCombo()')
	document.querySelector('#trickNameArea').setAttribute('onkeyup','disableAddTrick()')
	document.querySelector('#trickDescriptionArea').setAttribute('onkeyup','disableAddTrick()')
	document.querySelector('#addButton').disabled = true
	document.querySelector('#addComboButton').disabled = true
	
	document.querySelector('#welcome').innerText = "Welcome, " + localStorage.getItem('userName');
	
	setUpAddCustomTrick()
	setAddCombo()
}

const setAddCombo = () => {
	document.querySelector('#addComboButton').onclick = e=> {
		fetch(url+'/addCombo', {
            method:'POST',
            body:JSON.stringify({comboFrom:document.querySelector('#trickViewModalLabel').dataset.idtrick, comboInto:getIdFromNameCombo(document.querySelector('#comboIntoSelect').value)})
        })
        .then( response => response.json())
        .then(json=> {
            console.log(json)
			if(json.statusCode==400) alert("Error: adding combo Failed")
			else {
				//update combos, combo list
				updateCombos()
				setCombosInView(getTrickFromID(document.querySelector('#trickViewModalLabel').dataset.idtrick))
			}
        })
	}
}

const getIdFromNameCombo = (trickName) => {
	const selectDiv = document.querySelector('#comboIntoSelect')
	for (var i = 0; i < selectDiv.children.length; i++) {
		if (trickName == selectDiv.children[i].innerText) return selectDiv.children[i].dataset.idtrick
	}
	return -1
}

const fillComboTricks = () => {
	const comboIntoDiv = document.getElementById('comboIntoSelect')
	while(comboIntoDiv.firstChild){
		comboIntoDiv.removeChild(comboIntoDiv.firstChild)
	}
	//add options
	const buffer = document.createElement('option')
	buffer.innerText = "select a trick"
	comboIntoDiv.appendChild(buffer)
	const list = JSON.parse(localStorage.getItem('tricks'))
	for (var i = 0; i < list.length; i++) {
		const trick = document.createElement('option')
		trick.setAttribute('data-idTrick',list[i].idTrick)
		trick.innerText = list[i].trickName
		comboIntoDiv.appendChild(trick)
	}
}

const setUpAddCustomTrick = () => {
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

const disableAddTrick = () => {
	if (document.querySelector('#trickNameArea').value.length == 0 && document.querySelector('#trickDescriptionArea').value.length == 0) document.querySelector('#addButton').disabled = true
	if (document.querySelector('#trickNameArea').value.length != 0 && document.querySelector('#trickDescriptionArea').value.length != 0) document.querySelector('#addButton').disabled = false
}

const disableAddCombo = () => {
	if (document.querySelector('#comboIntoSelect').value == "select a trick") document.querySelector('#addComboButton').disabled = true
	if (document.querySelector('#comboIntoSelect').value != "select a trick") document.querySelector('#addComboButton').disabled = false
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
					//clear deleteButton
					const headerDiv = document.querySelector('#trickViewModalLabel')
					while(headerDiv.firstElementChild){
						headerDiv.removeChild(headerDiv.firstElementChild)
					}
					headerDiv.setAttribute('data-idTrick', trick.idTrick)
					//set title and descriptions
					headerDiv.innerText = trick.trickName
					document.querySelector('#trickDesBody').innerText = trick.trickDes
					//add delete button if custom
					if (trick.customUser != 0){
						const deleteButton = document.createElement('button')
						deleteButton.setAttribute('type','button')
						deleteButton.setAttribute('class','btn badge badge-danger deleteTrickButton')
						deleteButton.setAttribute('style','margin-left: 3px;margin-right: 3px;')
						deleteButton.innerText = 'x'
						headerDiv.appendChild(deleteButton)
					}
					//status Button
					const statusButton = document.createElement('button')
					statusButton.setAttribute('type','button')
					statusButton.setAttribute('class','btn badge badge-warning changeStatusButton')
					statusButton.setAttribute('style','margin-left: 3px;margin-right: 3px;')
					const statusNum = getStatusFromStorage(trick.idTrick)
					if (statusNum == 2) statusButton.innerText = "Mastered"
					if (statusNum == 1) statusButton.innerText = "Working On"
					else statusButton.innerText = "Not Learned"
					headerDiv.appendChild(statusButton)
					
					setCombosInView(trick)			
				}
			}
		}
    })
	//after you update the tricks, fill the combo tricks
	fillComboTricks()
}

const getStatusFromStorage = (idTrick) => {
	const list = JSON.parse(localStorage.getItem('status'))
	for (var i = 0; i < list.length; i++) {
		if (idTrick == list[i].idTrick) return list[i].status
	}
	return 0
}

const updateStatus = () => {
	fetch(url+'/getUserStatus', {
        method:'POST',
		body:JSON.stringify({idUser:localStorage.getItem('idUser')})
    })
    .then( response => response.json())
    .then(json=> {
        console.log(json)
		if(json.statusCode==400) alert("Error: getting statuss failed")
		else {
			localStorage.setItem('status', JSON.stringify(json.status))
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
		comboButton.setAttribute('style','margin-left: 5px;margin-right: 5px;')
		comboButton.innerText = getTrickFromID(comboList[i].comboInto).trickName + " x"
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
	fetch(url+'/getListOfCombos', {
            method:'GET'
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

