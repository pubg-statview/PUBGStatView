async function loadContent() {
    try {
        // Load header
        const headerResponse = await fetch('fragments/header.html');
        const headerContent = await headerResponse.text();
        document.querySelector('header').innerHTML = headerContent;

        // Load footer
        const footerResponse = await fetch('fragments/footer.html');
        const footerContent = await footerResponse.text();
        document.querySelector('footer').innerHTML = footerContent;
    } catch (error) {
        console.error('Error loading header or footer:', error);
    }
}

// Load the header and footer when the page is fully loaded
window.addEventListener('DOMContentLoaded', loadContent);

document.addEventListener("DOMContentLoaded", () => {
    const inputField = document.getElementById("inputText");
    const submitButton = document.getElementById("submitButton");

    submitButton.addEventListener("click", () => {
        const inputValue = inputField.value.trim(); // 입력값 가져오기 및 공백 제거

        // 간단한 검증: 입력값이 비어있으면 경고
        if (!inputValue) {
            alert("플레이어 이름을 입력하세요.");
            return;
        }

        // stats-view.html로 이동하며 입력값 전달
        const url = `stats-view.html?playerName=${encodeURIComponent(inputValue)}`;
        window.location.href = url;
    });
});
