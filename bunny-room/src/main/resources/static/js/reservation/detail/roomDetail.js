function createAppliedOptionsButtons() {
    const buttonsContainer = document.getElementById('appliedOptionsButtons');
    appliedOptions.forEach(appliedOption => {
        const button = document.createElement('button');
        button.textContent = appliedOption.appliedOptionDescription;
        button.addEventListener('click', () => toggleOption(appliedOption, button));
        buttonsContainer.appendChild(button);
    });
}

window.onload = () => {
    createAppliedOptionsButtons();
};