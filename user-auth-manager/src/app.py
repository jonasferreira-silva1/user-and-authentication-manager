from flask import Flask, render_template, request, jsonify
import requests

app = Flask(__name__)

# URL da API (no caso, sua aplicação Spring Boot)
API_URL = "http://localhost:8080/users"

# Rota para a página inicial, onde os usuários podem visualizar os dados


@app.route('/')
def index():
    # Chamando a API para listar os usuários
    response = requests.get(f"{API_URL}/listar")
    users = response.json()  # Recebe os dados em formato JSON
    return render_template('index.html', users=users)

# Rota para cadastrar um novo usuário


@app.route('/cadastrar', methods=['POST'])
def cadastrar():
    name = request.form['name']
    email = request.form['email']
    password = request.form['password']

    # Envia os dados para a API para cadastrar o usuário
    response = requests.post(f"{API_URL}/cadastrar", json={
        "name": name,
        "email": email,
        "password": password
    })

    # Redireciona para a página principal após o cadastro
    return index()

# Rota para atualizar um usuário (exemplo básico)


@app.route('/atualizar/<int:user_id>', methods=['POST'])
def atualizar(user_id):
    name = request.form['name']
    email = request.form['email']

    # Envia os dados para a API para atualizar o usuário
    response = requests.put(f"{API_URL}/atualizar/{user_id}", json={
        "name": name,
        "email": email
    })

    # Redireciona para a página principal após a atualização
    return index()

# Rota para deletar um usuário


@app.route('/deletar/<int:user_id>', methods=['POST'])
def deletar(user_id):
    # Envia a solicitação para deletar o usuário
    response = requests.delete(f"{API_URL}/deletar/{user_id}")

    # Redireciona para a página principal após a deleção
    return index()


if __name__ == '__main__':
    app.run(debug=True)
