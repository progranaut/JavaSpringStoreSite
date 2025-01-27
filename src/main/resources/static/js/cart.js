

async function displayProductInCart() {

    let response = await fetch('http://localhost:8080/store/all-products-in-basket');
    let productsInBasket = await response.json();

    console.log(productsInBasket);

    let cartContent = document.createElement("div");
    cartContent.classList.add('cart_content');

    let relationArray = Array.from(productsInBasket);

    console.log(relationArray);

    if (relationArray.length === 0) {
        cartContent.innerHTML = `
                        <p>Корзина пуста</p>
                        <a href="http://localhost:8080/">Просмотреть товары</a>
                    `;
        return cartContent;
    }

    relationArray.forEach(relation => {

        let div = document.createElement("div");
        div.classList.add('cart_product');
        div.setAttribute('data-product', relation.productDto.id);

        let divProdImg = document.createElement("div");
        divProdImg.classList.add('product_img');
        divProdImg.innerHTML = `
        <img src="${"/img/" + relation.productDto.imageUrl + ".jpg"}">
        `;
        div.appendChild(divProdImg);

        let divProdInfo = document.createElement("div");
        divProdInfo.classList.add('product_info');
        divProdInfo.innerHTML = `
        <p class="product_name">${relation.productDto.name}</p>
        <p class="product_price">${relation.productDto.price}</p>
        `;
        div.appendChild(divProdInfo);

        let divQuantityProduct = document.createElement("div");
        divQuantityProduct.classList.add('product_quantity_availability');

        let divQuantity = document.createElement('div');
        divQuantity.classList.add('product_quantity');
        let btnMinus = document.createElement('button');
        btnMinus.classList.add('minus_quantity');
        btnMinus.innerText = "-";
        btnMinus.addEventListener('click', async (e) => {
            // let request = new Request("http://localhost:8080/store/delete-product-from-basket/" + relation.productDto.id, {
            //     method: "DELETE"
            // });
            let request = new Request("http://localhost:8080/store/delete-product-from-basket/" + relation.productDto.id);
            let responseDel = await fetch(request);
            if (responseDel.status === 200) {
                let respJson = await responseDel.json();
                prodQuantity.innerText = respJson.quantity;
            } else {
                div.remove();
                let checkLast = await fetch('http://localhost:8080/store/all-products-in-basket');
                let checkLastJson = await checkLast.json();
                if (checkLastJson.length === 0) {
                    cartContent.innerHTML = `
                        <p>Корзина пуста</p>
                        <a href="http://localhost:8080/">Просмотреть товары</a>
                    `;
                }
            }
        });
        divQuantity.appendChild(btnMinus);

        let prodQuantity = document.createElement('p');
        prodQuantity.innerText = relation.quantity;
        divQuantity.appendChild(prodQuantity);

        let btnPlus = document.createElement("button");
        btnPlus.classList.add('plus_quantity');
        btnPlus.innerText = "+";
        btnPlus.addEventListener('click', async (e)=>{
            // let response = await fetch("http://localhost:8080/store/add-in-basket/" + relation.productDto.id, {
            //     method: "POST"
            // });
            let response = await fetch("http://localhost:8080/store/add-in-basket/" + relation.productDto.id);
            if (response.status === 200) {
                let respJson = await response.json();
                prodQuantity.innerText = respJson.quantity;
            }
            if (response.status === 406) {
                divAvailability.style.backgroundColor = "RED";
            }
        });
        divQuantity.appendChild(btnPlus);

        divQuantityProduct.appendChild(divQuantity);

        let divAvailability = document.createElement('div');
        divAvailability.classList.add('product_availability');
        divAvailability.innerHTML = `
            Наличие: ${relation.productDto.availability} шт.
        `;
        divQuantityProduct.appendChild(divAvailability);

        div.appendChild(divQuantityProduct);

        cartContent.appendChild(div);
    });

    let orderBtn = document.createElement('button');
    orderBtn.innerText = "Оформить заказ";
    orderBtn.setAttribute('id','start_order');
    orderBtn.addEventListener('click', async (e)=>{
        orderBtn.style.visibility = "hidden";
        let dispUser = await displayUser();
        cartContent.appendChild(dispUser);
        let confirmBtn = document.createElement('button');
        confirmBtn.innerText = "Подтвердить заказ";
        confirmBtn.setAttribute('id','confirm_order');
        confirmBtn.addEventListener('click', (e) => {
            fetch('http://localhost:8080/store/add-order').then(response => {
                if (response.status === 200) {
                    cartContent.innerHTML = `
                        <p>Заказ успешно подтвержден</p>
                    `;
                    let inStoreBtn = document.createElement('button');
                    inStoreBtn.innerText = "Продолжить покупки";
                    inStoreBtn.addEventListener('click', async (e)=>{
                        cartContent.innerHTML = ``;
                        cartContent.appendChild(await displayProducts());
                    });
                    cartContent.appendChild(inStoreBtn);
                    let inUserBtn = document.createElement('button');
                    inUserBtn.innerText = "Просмотреть заказы";
                    inUserBtn.addEventListener('click', async (e)=>{
                        cartContent.innerHTML = ``;
                        cartContent.appendChild(await displayUserOrders());
                    });
                    cartContent.appendChild(inUserBtn);
                } else {
                    let message = document.createElement('p');
                    message.innerText = "Необходимо заполнить все поля и сохранить их!";
                    cartContent.insertBefore(message ,confirmBtn);
                }
            });
        });
        cartContent.appendChild(confirmBtn);
    });

    cartContent.appendChild(orderBtn);

    return cartContent;

}

async function displayProductInCartNotReg() {
    let response = await fetch('http://localhost:8080/store/all-products');
    let products = await response.json();
    let productsArray = Array.from(products);



    let cartContent = document.createElement("div");
    cartContent.classList.add('cart_content');

    if (document.cookie.length === 0) {
        cartContent.innerHTML = `
                        <p>Корзина пуста</p>
                        <a href="http://localhost:8080/">Просмотреть товары</a>
                    `;
        return cartContent;
    }

    productsArray.forEach(product => {
        let cookie = document.cookie;
        let cookieArray = cookie.split(";");
        cookieArray.forEach(cook => {
            if (cook.split("=")[0].trim() === product.id) {

                let div = document.createElement("div");
                div.classList.add('cart_product');
                div.setAttribute('data-product', product.id);

                let divProdImg = document.createElement("div");
                divProdImg.classList.add('product_img');
                divProdImg.innerHTML = `
                    <img src="${"/img/" + product.imageUrl + ".jpg"}">
                `;
                div.appendChild(divProdImg);

                let divProdInfo = document.createElement("div");
                divProdInfo.classList.add('product_info');
                divProdInfo.innerHTML = `
                    <p class="product_name">${product.name}</p>
                    <p class="product_price">${product.price}</p>
                `;
                div.appendChild(divProdInfo);

                let divQuantityProduct = document.createElement("div");
                divQuantityProduct.classList.add('product_quantity_availability');

                let divQuantity = document.createElement('div');
                divQuantity.classList.add('product_quantity');
                let btnMinus = document.createElement('button');
                btnMinus.classList.add('minus_quantity');
                btnMinus.innerText = "-";
                btnMinus.addEventListener('click', async (e) => {

                    let value = 0;
                    document.cookie.split(";").forEach(cookie => {
                        if (cookie.split("=")[0].trim() === product.id) {
                            value = cookie.split("=")[1].trim();
                        }
                    });
                    if (value > 1) {
                        document.cookie = product.id + "=" + --value;
                        prodQuantity.innerText = value;
                    } else {
                        document.cookie = product.id + "=" + value + "; max-age=0";
                        div.remove();
                        if (document.cookie.length === 0) {
                            cartContent.innerHTML = `
                            <p>Корзина пуста</p>
                            <a href="http://localhost:8080/">Просмотреть товары</a>
                        `;
                        }
                    }

                });
                divQuantity.appendChild(btnMinus);

                let prodQuantity = document.createElement('p');
                prodQuantity.innerText = cook.split("=")[1].trim();
                divQuantity.appendChild(prodQuantity);

                let btnPlus = document.createElement("button");
                btnPlus.classList.add('plus_quantity');
                btnPlus.innerText = "+";
                btnPlus.addEventListener('click', async (e)=>{
                    let value = 0;
                    document.cookie.split(";").forEach(cookie => {
                        if (cookie.split("=")[0].trim() === product.id) {
                            value = cookie.split("=")[1].trim();
                        }
                    });
                    if (value < product.availability) {
                        document.cookie = product.id + "=" + ++value;
                        prodQuantity.innerText = value;
                    } else {
                        divAvailability.style.backgroundColor = "RED";
                    }
                });
                divQuantity.appendChild(btnPlus);

                divQuantityProduct.appendChild(divQuantity);

                let divAvailability = document.createElement('div');
                divAvailability.classList.add('product_availability');
                divAvailability.innerHTML = `
                    Наличие: ${product.availability} шт.
                `;
                divQuantityProduct.appendChild(divAvailability);

                div.appendChild(divQuantityProduct);

                cartContent.appendChild(div);
            }
        });
    });

    let regHref = document.createElement('p');
    regHref.innerHTML = `
        Для оформления заказа необходимо выполнить <a href="/login">вход</a>.
    `;
    cartContent.appendChild(regHref);

    return cartContent;
}

// class Product {
//
//     constructor(/*id, serialNumber, name, price*/) {
//         // this.id = id;
//         // this.serialNumber = serialNumber;
//         // this.name = name;
//         // this.price = price;
//     }
//
// }