function chart(url){
    const radius = 10;
    // 1. 数据处理
    getJson(url, function (myjson){
        const data = JSON.parse(myjson);
        const nodes = data.nodes.map(d => ({...d}));
        const links = data.links.map(d => ({...d}));
        const group1 = nodes.filter(function (d){
            if (d.group == 1){
                return true;
            }
        });
        const group2 = nodes.filter(function (d){
            if (d.group == 2){
                return true;
            }
        });
        const group3 = nodes.filter(function (d){
            if (d.group == 3){
                return true;
            }
        });
        const group4 = nodes.filter(function (d){
            if (d.group == 4){
                return true;
            }
        });
        // for (let i = 0; i < group1.length; i++) {
        //     console.log("group1[" + i +"]: ", group1[i].time);
        // }
        console.log("group1:", group1.length);
        console.log("group2:", group2.length);
        console.log("group3:", group3.length);
        console.log("group4:", group4.length);
        // 检验nodes和links的数据
        // console.log("Nodes:", nodes);
        // console.log("Links:", links);

        // 2. 创建模型

        // svg画布
        const svg = d3.select("div")
            .append("svg")
            .attr("id", "svg")
            .attr("class", "svg");
        var width = parseFloat(window.getComputedStyle(document.getElementById("svg")).width);
        var height = parseFloat(window.getComputedStyle(document.getElementById("svg")).height);
        svg.attr("viewBox", [0, 0, width, height]);
        let group1_distance = (1/((group1.length - group1distance()) - 1))*(width*0.8);
        // 如果宽高是通过内联样式设置的，使用 getComputedStyle() 获取宽度和高度，然后使用 parseFloat() 去掉 px 单位。
        // 如果宽高是通过属性设置的，可以直接使用 getAttribute() 获取 width 和 height 的值。
        // console.log(width);
        // console.log(height);
        var output = document.getElementById("output");
        output.appendChild(document.createTextNode("svg_width: " + width + " , svg_height: " + height));
        // 力模型
        const simulation = d3.forceSimulation(nodes)
            .force("link", d3.forceLink(links).id(d => d.id).iterations(10)
                .distance(function (link){return link_distance(link);})
                .strength(function (link){
                return 1 / Math.min(count(link.source),count(link.target));
            })
            )
            // .force("charge", d3.forceManyBody(function (d){
            //     if (d.group == 1){
            //         return -radius*5;
            //     }else{
            //         return -radius;
            //     }
            // }).distanceMax(800).distanceMin(0))
            // .force("center", d3.forceCenter(width / 2, height / 2))
            .force("collide", d3.forceCollide(function (d){
                if (d.group == 1){
                    return radius*5;
                }else{
                    return radius*2;
                }
            }))
            .force("y", d3.forceY(height / 2).strength(function (d){
                if (d.group == 1){
                    return 0.7;
                }else{
                    return -0.05;
                }
            }))
            .on("tick", ticked);
        // 另一个力模型
        // const simulation2 = d3.forceSimulation(group1)

        function count(d){
            let i = 0;
            links.forEach(function(l){
                if (l.source === d || l.target === d){
                    i++;
                }
            });
            return i;
        }
        // 下面调用
        function group1distance(){
            let n = 0;
            for (let i = 0; i < group1.length; i++){
                for (let j = i+1; j < group1.length; j++){
                    if (group1[i].time === group1[j].time){
                        n++;
                    }else{
                        i = j-1;
                        break;
                    }
                }
            }
            return n;
        };
        // 节点间的连接长度函数
        function link_distance(link){
            if (link.source.group == 1 && link.target.group == 1){
                return group1_distance;
            }else{
                return radius*7;
            }
        }
        // 连接
        // selectAll()中使用.link和 link 的区别：
        // .link 表示使用类选择器，link表示使用元素标签选择器
        // 不需要 .enter().append("line"),
        // 因为 v5以上的 d3.join() 方法已经涵盖了 .enter()、.update() 和 .exit() 的功能。
        var svg_link = svg.selectAll(".link")
            .data(links)
            .join("line")
            .attr("class", "line")
            .attr("stroke", "blue")
            .attr("stroke-width", 1);
        // 节点
        var svg_node = svg.selectAll(".node")
            .data(nodes)
            .join("circle")
            .attr("class", "node")
            .attr("r", radius)
            .attr("fill", function(d) {
                if (d.group == 1){
                    return "#40E0D0";
                } else if (d.group == 2){
                    return "#fcd04b";
                } else if (d.group == 3){
                    return "#ff0000";
                } else{
                    return "#570000"
                }
            });

        // 3. 对元素模型的各种操作控制(control)

        // 控制各group的位置
        // x轴 为时间轴
        nodes.filter(function(d){
            if (d.group == 1){
                console.log(d.id + " : " + d.time * width);
                d.fx = d.time * width;
                // d.fy = height/2;
            }else{
                d.x = d.x;
            }
        });
        // 拖动行为
        const dragHandler = d3.drag()
           .on("start", dragstart)
           .on("drag", draged)
           .on("end", dragend);
        var dragnodes = svg_node.filter(function (d){
            if (d.group == 2 || d.group == 3 || d.group == 4){
               return d;
            }
        });
        dragnodes.call(dragHandler);
        // 自定义工具提示给节点显示文字（名称）
        const tooltip = d3.select("body").append("div")
            .attr("class", "tooltip")
            .style("opacity", 0);
        // 过度;实现颜色的过度转换
        d3.select("svg")
            .transition()
            .style("background-color", "#6050a0");

        // 4. 各种model; 即实现函数,用于给 3步中的控制器使用

        //
        function ticked() {
            svg_link.attr("x1", d => d.source.x)
                .attr("y1", d => d.source.y)
                .attr("x2", d => d.target.x)
                .attr("y2", d => d.target.y);
            svg_node.attr("cx", d => d.x)
                .attr("cy", d => d.y);
        }
        // 节点的拖动以及拖动节点高亮实现函数
        // event传递事件， d传递当前元素的一些属性
        function dragstart(event, d, nodeElement){
            if (!event.active) {simulation.alphaTarget(0.3).restart();}
            // event.subject 属性，通常是拖拽的“主体”，即被拖拽的元素的数据对象或位置信息。
            event.subject.fx = event.subject.x;
            event.subject.fy = event.subject.y;
            // 高亮开始
            svg_node.classed("highlight", false);
            svg_node.classed("fade", false);
            d3.select(this).classed('highlight', true);
            // 定义连接判断函数
            const isConnected = function (a, b) {
                return links.forEach(function (l) {
                    return (l.source.id === a && l.target.id === b)
                        ||(l.source.id === b && l.target.id === a);}
                );
            }
            // 为所有节点添加淡出效果，除了与当前节点相连的节点
            svg_node.classed("fade", function (n) {
                return (n.id !== d.id) && !isConnected(d.id, n.id)});
            console.log('Clicked node: click_start');
            // 显示节点的 ID
            d3.select(this).classed("active", true);
            tooltip.transition().duration(200).style("opacity", 1);
            tooltip.html(d.id); // 在html生成内容
            // d3.pointer(event[, target])函数, event：鼠标或触摸事件对象,
            // target（可选）：要计算相对坐标的目标元素。默认是事件的当前目标（event.currentTarget）。
            const [x, y] = d3.pointer(event, svg);
            tooltip.style("left", (x + 10) + "px")
                   .style("top", (y - 10) + "px");
        }
        function draged(event){
            event.subject.fx = Math.max(radius, Math.min(width - radius, event.x));
            event.subject.fy = Math.max(radius, Math.min(height - radius, event.y));
            // 更新工具提示的位置
            const [x, y] = d3.pointer(event, svg);
            tooltip.style("left", (x + 10) + "px")
                   .style("top", (y - 10) + "px");
        }
        function dragend(event){
            if (!event.active) {simulation.alphaTarget(0)}
            event.subject.fx = null;
            event.subject.fy = null;
            // 高亮结束
            svg_node.classed("highlight", false);
            svg_node.classed("fade", false);
            // 结束显示标签名
            d3.select(this).classed("active", false);
            tooltip.transition().duration(200).style("opacity", 0);
            console.log('Clicked node: click_end');
        }
    });
}
/*
读取 json数据
 */
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
// 运行
function activeD3 (){
    var url = "http://localhost:8080/Beer-v1/d3force.do";
    chart(url);
}
addLoadEvent(activeD3);
