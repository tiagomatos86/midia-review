// URL da API
const API_URL = "http://localhost:8080/midias";

// Renderização da lista
function renderLista(midias) {
    const lista = document.getElementById("lista-midias");
    lista.innerHTML = "";

    if (!midias || midias.length === 0) {
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
}

// Função para buscar e exibir todas as mídias
async function carregarMidias() {
    const lista = document.getElementById("lista-midias");
    lista.innerHTML = "<p>Carregando mídias...</p>";

    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error("Erro ao buscar mídias: " + response.status);
        const midias = await response.json();
        renderLista(midias);
    } catch (error) {
        console.error(error);
        lista.innerHTML = "<p>Erro ao carregar mídias.</p>";
    }
}

// Função para buscar mídias com base no tipo e termo
async function buscarMidias() {
    const tipo = document.getElementById("select").value;
    const termo = document.querySelector(".input-buscar").value.trim();

    if (!termo) {
        carregarMidias();
        return;
    }

    const lista = document.getElementById("lista-midias");
    lista.innerHTML = "<p>Carregando resultados...</p>";

    const url = `${API_URL}/search?${tipo}=${encodeURIComponent(termo)}`;

    try {
        const response = await fetch(url);
        if (!response.ok) throw new Error("Erro ao buscar mídias: " + response.status);
        const midias = await response.json();
        renderLista(midias);
    } catch (error) {
        console.error(error);
        lista.innerHTML = "<p>Erro ao buscar mídias.</p>";
    }
}

// Inicialização ao carregar a página
document.addEventListener("DOMContentLoaded", () => {
    carregarMidias();

    const form = document.getElementById("form-buscar");
    if (form) {
        form.addEventListener("submit", (e) => {
            e.preventDefault();
            buscarMidias();
        });
    }

    const logo = document.getElementById("logo");
    if (logo) {
        logo.addEventListener("click", (e) => {
            e.preventDefault();
            carregarMidias();
        });
    }

    const btnAdd = document.getElementById("btn-add-midia");
    if (btnAdd) {
        btnAdd.addEventListener("click", () => {
            window.location.href = "form-midia.html";
        });
    }

    // Captura o formulário de cadastro de mídia
    const formMidia = document.getElementById("form-midia");
    if (formMidia) {
        formMidia.addEventListener("submit", async (e) => {
            e.preventDefault();

            const formData = new FormData(formMidia);
            const midia = Object.fromEntries(formData.entries());

            // Normaliza tipos numéricos
            midia.anoLancamento = Number(midia.anoLancamento);
            midia.nota = Number(midia.nota);

            try {
                const resp = await fetch(API_URL, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(midia)
                });

                if (!resp.ok) throw new Error("Erro ao cadastrar mídia: " + resp.status);

                alert("Mídia cadastrada com sucesso!");
                window.location.href = "index.html"; // volta para a lista
            } catch (err) {
                console.error(err);
                alert("Erro ao cadastrar mídia. Tente novamente.");
            }
        });
    }
});
