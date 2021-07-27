const url = 'https://40dxits28f.execute-api.us-east-1.amazonaws.com/Alpha'
window.onload = () => {
	
	document.querySelector('#welcome').innerText = "Welcome, " + localStorage.getItem('userName');
	
	document.querySelector('#generateButton').onclick = e=> {
		let trickList = JSON.parse(localStorage.getItem('tricks'))
		let comboList = JSON.parse(localStorage.getItem('combos'))
		let statusList = JSON.parse(localStorage.getItem('status'))
		let comboLength = document.querySelector('#comboLength').value.length
		let isNotLearned = document.querySelector('#inlineCheckbox1').checked
		let isWorkingOn = document.querySelector('#inlineCheckbox2').checked
		let isMastered = document.querySelector('#inlineCheckbox3').checked
		
		let comboString = ""
		let queryedTricks = []
		
		//if combo length has something, or if any of the status's are checked
		if(comboLength != 0 && (isNotLearned||isWorkingOn||isMastered)){
			//reduce trick list based on status checked
			for (let i = 0; i < trickList.length; i++) {
				//add everything in status list
				for (let j = 0; j < statusList.length; j++) {
					if(trickList[i].idTrick == statusList[j].idTrick){
						//if mastered
						if(isMastered && statusList[j].status == 2){
							queryedTricks.push(trickList[i])
						}
						//if working on
						if(isWorkingOn && statusList[j].status == 1){
							queryedTricks.push(trickList[i])
						}
						//if not learned
						if(isNotLearned && statusList[j].status == 0){
							queryedTricks.push(trickList[i])
						}
					}
				}
				//add the remaining not learned, that was not in status list
				if(isNotLearned && !isInStatusList(statusList,trickList[i].idTrick)){
					queryedTricks.push(trickList[i])
				}
			}
			//add trick combos until you hit max length or until you cannot combo anymore
			let counter = 0
			let randInd = Math.trunc(Math.random()*queryedTricks.length)
			let queryedCombos = getComboListFromTrick(queryedTricks, comboList,queryedTricks[randInd].idTrick)
			comboString = comboString.concat(queryedTricks[randInd].idTrick)
			counter++
			while(counter < document.querySelector('#comboLength').value && queryedCombos.length > 0){
				//choose a random combo
				randInd = Math.trunc(Math.random()*queryedCombos.length)
				//add trick
				comboString = comboString.concat(queryedCombos[randInd].comboInto)
				//update queryedCombos
				queryedCombos = getComboListFromTrick(queryedTricks, comboList,queryedCombos[randInd].comboInto)
				//update counter
				counter++
			}
			document.querySelector('#listOfTricksDiv').innerHTML = comboString
		}else{
			alert('Make sure to select a combo length and status')
		}
	}
}

function isInQueryedTrickList(queryedTricks, idTrick){
	for (let i = 0; i < queryedTricks.length; i++) {
		if(queryedTricks[i].idTrick == idTrick){
			return true
		}
	}
	return false
}

function getComboListFromTrick(queryedTricks, comboList, idTrick){
	let list = []
	//add the combo if it starts with idTrick AND combo into is in the queryedList
	for (let i = 0; i < comboList.length; i++) {
		if(comboList[i].comboFrom == idTrick && isInQueryedTrickList(queryedTricks, comboList[i].comboInto)){
			list.push(comboList[i])
		}
	}
	return list
}

function isInStatusList(statusList, idTrick){
	for (let i = 0; i < statusList.length; i++) {
		if(statusList[i].idtrick == idTrick){
			return true;
		}
	}
	return false;
}

function getTrickFromID(id) {
	const list = JSON.parse(localStorage.getItem('tricks'))
	for (var i = 0; i < list.length; i++) {
		if (id == list[i].idTrick){
			return list[i]
		}
	}
	return null
}
