function updateInterests() {
    const checkboxes = document.querySelectorAll('input[name="interest"]:checked');
    const selectedValues = Array.from(checkboxes).map(checkbox => checkbox.value);
    document.getElementById('selectedInterests').value = selectedValues.join(' ');
}