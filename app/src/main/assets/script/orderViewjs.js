


class HTTP{
    constructor(){
        
    }
    XMLHtttp(father,URL){
        var jsonObj;
        var a;
        var myajax=new XMLHttpRequest()
        //alert(myajax) //IE6及其以下版本不支持
        //2.连接服务器
        // open(方法，文件路径，异步传输)
        myajax.open('GET',URL,true);
        //3.发送请求
        myajax.send(null);
        //4.接受返回的数据
        myajax.onreadystatechange=function(){ //1、onreadystatechange属性的使用时连接函数
            if(myajax.readyState==4){ //2、readyState是XMLHttpRequest对象的属性，所以要是对象.属性的方式访问
                if (myajax.status==200) {
                    // alert('成功'+myajax.responseText);
                    var data = myajax.responseText;
                    // alert(typeof(data))
                    // jsonObj = eval('('+data+')');
                    jsonObj = JSON.parse(data);
                    let list = jsonObj.data.list;
                    console.log(list);
                     for(let i =0;i<list.length;i++){
                        if(list[i].orderItemvos!=[]){
                            for(let j =0;j<list[i].orderItemvos.length;j++){
                              createOrders( father,list[i].orderItemvos[j].product.purl , list[i].orderItemvos[j].product.pname,list[i].orderItemvos[j].subtotal,list[i].time,"催促商家");
                            }
                     }
                      }
                    // alert(jsonObj[5].name);
                    //3、js中+号连接字符串   4、XMLHttpRequest对象的responseText属性获取从服务器返回的数据
                }else{ 
                        alert('失败'+myajax.status)
                }
            }
        }
        return jsonObj;
    }
}


function createOrders( father,picturesURL,name, priceNum,time,button){
    var orders = document.createElement('div');
    orders.className = "orders";
    var img = document.createElement('img');
    img.src = picturesURL;
    orders.appendChild(img);

    var ordersTitle = document.createElement('div');
    ordersTitle.className = "ordersTitle";
    var productName = document.createElement('p');
    productName.className = "productName";
    productName.innerHTML = name;
    var price = document.createElement('p');
    price.className = "price";
    price.innerHTML = "合计:"
    var priceNumber = document.createElement('p');
    priceNumber.className = "priceNumber";
    priceNumber.innerHTML = "￥"+priceNum;
    ordersTitle.appendChild(productName);
    ordersTitle.appendChild(price);
    ordersTitle.appendChild(priceNumber);
    orders.appendChild(ordersTitle);

    var ordersRight = document.createElement('div');
    ordersRight.className = "ordersRight";
    var statu = document.createElement('p');
    statu.className = "statu";
    statu.innerHTML = time;
    var ordersBtn = document.createElement('button') 
    ordersBtn.className = "ordersBtn";
    ordersBtn.innerHTML = button;
    ordersBtn.id = "btn";
    ordersRight.appendChild(statu);
    ordersRight.appendChild(ordersBtn);
    orders.appendChild(ordersRight);
    father.insertBefore(orders,father.firstChild);

}

function tiaozhuan(){
    window.location.href = "ShoppingCartInterface.html"
}
 
document.getElementById("btn").onclick = function(){window.Android.loading()}

var father = document.querySelector('#bodys');
var jsonOBJ = new HTTP();


var jsonDataOBJ = jsonOBJ.XMLHtttp(father,'http://personal2.shenzhuo.vip:18019/userservice/orders/getOrderList/1279088273653854209');

let bid = "1278278746071109633";