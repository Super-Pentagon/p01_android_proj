


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

                    for(var i in jsonObj){
                        createOrders( father,jsonObj[i].pictureUrl,jsonObj[i].name, jsonObj[i].price,"预计12：30送达","再来一单");
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
    ordersRight.appendChild(statu);
    ordersRight.appendChild(ordersBtn);
    orders.appendChild(ordersRight);
    father.insertBefore(orders,father.firstChild);

}
var btn = document.querySelector('.btn');

var father = document.querySelector('#bodys');
var jsonOBJ = new HTTP();

var jsonDataOBJ = jsonOBJ.XMLHtttp(father,'https://run.mocky.io/v3/46b38e9a-22ed-4ac7-88c8-bd6380a0b1be');
function tiaozhuan(){
    window.location.href = "ShoppingCartInterface.html"
}
