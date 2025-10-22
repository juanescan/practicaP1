function loadGetMsg() {
  let list = document.getElementById("list").value;
  let value = document.getElementById("value").value;
  let isLinear = document.getElementById("searchType").checked;
  let operation = isLinear ? "/linearsearch" : "/binarysearch";



  fetch(`${operation}?list=${list}&value=${value}`)
    .then(res => res.json())
    .then(data => makeBeautiful(data));
}

function makeBeautiful(json) {
  let table = '<table border="1">';
  for (let key in json) {
    table += `<tr><td>${key}</td><td>${json[key]}</td></tr>`;
  }
  table += '</table>';
  document.getElementById('getrespmsg').innerHTML = table;
}
