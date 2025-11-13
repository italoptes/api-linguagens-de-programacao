/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formNovaLinguagem");

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const formData = new FormData();
        formData.append("nome", document.getElementById("nome").value);
        formData.append("criador", document.getElementById("criador").value);
        formData.append("anoCriacao", document.getElementById("anoCriacao").value);
        formData.append("paradigma", document.getElementById("paradigma").value);
        formData.append("foto", document.getElementById("foto").files[0]);

        try {
            const response = await fetch("/api/linguaguem", {
                method: "POST",
                body: formData
            });

            if (response.ok) {
                alert("Linguagem adicionada com sucesso!");
                form.reset();
                window.location.reload();
            } else {
                alert("Erro ao salvar linguagem.");
            }
        } catch (error) {
            alert("Erro de conexão com o servidor.");
            console.error(error);
        }
    });
});

// Fecha o modal se clicar fora dele
window.onclick = function (event) {
    const modal = document.getElementById("modalDetalhes");
    if (event.target === modal) {
        modal.style.display = "none";
    }
};
