let registerBtn = document.getElementById('register_btn');
let loginBtn = document.getElementById('login_btn');
let logReg = document.getElementById('log_reg');

registerBtn.addEventListener('click', (e) => {

    logReg.innerHTML = `
        <div class="form_content">
            <form id="log_reg_form" class="form" action="/store/user-registration" method="post">
                 
                 <label for="name">Имя: </label>
                 <input type="text" name="name" id="name">
                 
                 <label for="username">Логин: </label>
                 <input type="text" name="username" id="username">
                 
                 <label for="password">Пароль: </label>
                 <input type="password" name="password" id="password">
                 
                 <button id="reg_btn" type="submit">Зарегистрировать</button>
            </form>
        </div>
    `;

    let reg_btn = document.getElementById('reg_btn');
    reg_btn.addEventListener('click', async  (e) => {
        e.preventDefault();
        let form = document.getElementById('log_reg_form');
        let user = {
            username: form.username.value,
            password: form.password.value
        }
        console.log(user);
        await fetch('http://localhost:8080/auth/user-registration', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        }).then(response => {
            if (response.status === 200) {
                window.location.replace("http://localhost:8080/login");
            }
        });
    });

});

loginBtn.addEventListener('click', (e) => {

    logReg.innerHTML = `
        <div class="form_content">
            <form id="log_reg_form" class="form" action="/login" method="post">
                <label htmlFor="username">Логин: </label>
                <input type="text" name="username" id="username">
                <label htmlFor="password">Пароль: </label>
                <input type="password" name="password" id="password">
                <button id="log2_btn" type="submit">Войти</button>
            </form>
        </div>
    `;

    let log_btn = document.getElementById('log2_btn');
    log_btn.addEventListener('click', async  (e) => {
        e.preventDefault();
        let form = document.getElementById('log_reg_form');
        let user = {
            username: form.username.value,
            password: form.password.value,
        }
        console.log(user);
        await fetch('http://localhost:8080/auth/sign-in', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        }).then(response => {
            if (response.status === 200) {
                window.location.replace("http://localhost:8080/home");
            }
        });
    });

});