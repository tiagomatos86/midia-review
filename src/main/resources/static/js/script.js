// URL da sua API
const API_URL = "http://localhost:8080/midias";

// Função para buscar e exibir as mídias
async function carregarMidias(params) {
    try{
        const response = await fetch(API_URL)
        if (!response.ok) {
            throw new Error("Erro ao buscar mídias: " + response.status)
        }
        const midias = await response.json();

        const lista = document.getElementById("lista-midias");
        lista.innerHTML = "";

        midias.forEach(midia => {
            const div = document.createElement("div")
            div.className = "midia";
            div.innerHTML = `
                <h2>${midia.titulo} (${midia.anoLancamento})</h2>
                <p><strong>Tipo:</strong> ${midia.tipo}</p>
                <p><strong>Nota:</strong> ${midia.nota}</p>
                <p><strong>Resenha:</strong> ${midia.resenha || "Sem resenha"}</p>
                ${midia.imageUrl ? `<img src="${midia.imageUrl}" alt="${midia.titulo}">` : ""}
            `
            lista.appendChild(div);
        })
    } catch (error) {
        console.error(error)
        document.getElementById("lista-midias").innerHTML = "<p>Erro ao carregar mídias.</p>"
    }
}

// Carregar mídias ao abrir a página
carregarMidias();