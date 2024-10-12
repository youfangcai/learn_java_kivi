

// function getJson (){
//     var req = new XMLHttpRequest();
//     req.open("GET", "https://api.github.com/users/octocat", true);
//     req.setRequestHeader("Accept", "application/json");
//     req.onreadystatechange = function () {
//         if (req.readyState == 4){
//             if (req.status == 200){
//                 var div = document.getElementById("div");
//                 var myjson = JSON.parse(req.responseText);
//                 var jsonStr = JSON.stringify(myjson);
//                 var text = document.createTextNode(jsonStr);
//                 div.appendChild(text);
//             }
//             else{
//                 console.error("Request failed with status: " + req.status);
//             }
//         }};
//     req.send();
// }
// addLoadEvent(getJson);

function getJson (url, callback){
    var req = new XMLHttpRequest();
    req.open("GET", url, true);
    req.setRequestHeader("Accept", "application/json");
    req.onreadystatechange = function () {
        if (req.readyState == 4){
            if (req.status == 200){
                callback(req.responseText);
            }
            else{
                console.error("Request failed with status: " + req.status);
            }
        }};
    req.send();
}
function chart(url){
    getJson(url, function (myjson){
        var data = JSON.parse(myjson);
        nodes = data.nodes.map(d => ({...d}));
        links = data.links.map(d => ({...d}));

        console.log("Nodes:", nodes);
        console.log("Links:", links);
    });
}

// function output(url){
//     getJson(url, function  (myjson){
//         var div = document.getElementById("div");
//         var jsonObject = JSON.parse(myjson);
//         var jsonStr = JSON.stringify(jsonObject,null,2);
//         var text = document.createTextNode(jsonStr);
//         div.appendChild(text);
//     });
// }
// 运行
function activeD3 (){
    var url = "http://10.41.141.5:8080/Beer-v1/d3force.do";
    chart(url);
}
addLoadEvent(activeD3);

// function getJson() {
//     fetch("http://10.41.141.5:8080/Beer-v1/d3force.do", {
//         method: "GET",
//         headers: {
//             "Accept": "application/json"
//         }
//     })
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error("Network response was not ok: " + response.statusText);
//             }
//             return response.json(); // 解析 JSON
//         })
//         .then(data => {
//             var div = document.getElementById("div");
//             div.innerHTML = JSON.stringify(data, null, 2); // 格式化显示 JSON
//         })
//         .catch(error => {
//             console.error("There was a problem with the fetch operation:", error);
//         });
// }
//
// window.onload = getJson; // 页面加载时调用 getJson