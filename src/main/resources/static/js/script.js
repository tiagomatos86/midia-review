// URL da API
const API_URL = "http://localhost:8080/midias";

// Função para buscar e exibir todas as mídias
async function carregarMidias() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error("Erro ao buscar mídias: " + response.status);
        }
        const midias = await response.json();

        const lista = document.getElementById("lista-midias");
        lista.innerHTML = "";

        midias.forEach(midia => {
            const div = document.createElement("div");
            div.className = "midia";
            div.innerHTML = `
                <h2>${midia.titulo} (${midia.anoLancamento})</h2>
                <p><strong>Tipo:</strong> ${midia.tipo}</p>
                <p><strong>Nota:</strong> ${midia.nota}</p>
                <p><strong>Resenha:</strong> ${midia.resenha || "Sem resenha"}</p>
                ${midia.imageUrl ? `<img src="${midia.imageUrl}" alt="${midia.titulo}">` : ""}
            `;
            lista.appendChild(div);
        });
    } catch (error) {
        console.error(error);
        document.getElementById("lista-midias").innerHTML = "<p>Erro ao carregar mídias.</p>";
    }
}

// Função para buscar mídias com base no tipo e termo
async function buscarMidias() {
    const tipo = document.getElementById("select").value;
    const termo = document.querySelector(".input-buscar").value.trim();

    if (!termo) {
        carregarMidias(); // se o campo estiver vazio, carrega tudo
        return;
    }

    const url = `${API_URL}/search?${tipo}=${encodeURIComponent(termo)}`;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error("Erro ao buscar mídias: " + response.status);
        }
        const midias = await response.json();

        const lista = document.getElementById("lista-midias");
        lista.innerHTML = "";

        if (midias.length === 0) {
            lista.innerHTML = "<p>Nenhuma mídia encontrada.</p>";
            return;
        }

        midias.forEach(midia => {
            const div = document.createElement("div");
            div.className = "midia";
            div.innerHTML = `
                <h2>${midia.titulo} (${midia.anoLancamento})</h2>
                <p><strong>Tipo:</strong> ${midia.tipo}</p>
                <p><strong>Nota:</strong> ${midia.nota}</p>
                <p><strong>Resenha:</strong> ${midia.resenha || "Sem resenha"}</p>
                ${midia.imageUrl ? `<img src="${midia.imageUrl}" alt="${midia.titulo}">` : ""}
            `;
            lista.appendChild(div);
        });
    } catch (error) {
        console.error(error);
        document.getElementById("lista-midias").innerHTML = "<p>Erro ao buscar mídias.</p>";
    }
}

// Inicialização ao carregar a página
document.addEventListener("DOMContentLoaded", () => {
    // Carrega todas as mídias por padrão
    carregarMidias();

    // Evento de busca ao enviar o formulário
    const form = document.getElementById("form-buscar");
    form.addEventListener("submit", (e) => {
        e.preventDefault();
        buscarMidias();
    });

    // Evento de clique no logo para recarregar tudo
    const logo = document.getElementById("logo");
    logo.addEventListener("click", (e) => {
        e.preventDefault();
        carregarMidias();
    });
});
