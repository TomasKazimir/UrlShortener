const shortenBtn = document.getElementById('shorten-btn');
const urlInput = document.getElementById('url-input');
const resultDiv = document.getElementById('shorten-result');

shortenBtn.addEventListener('click', async () => {
    const url = urlInput.value.trim();
    resultDiv.innerHTML = '';
    if (!url) return;

    try {
        const response = await fetch('/api/shorten', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ url })
        });

        if (!response.ok) {
            const text = await response.text();
            throw new Error(`${response.status} ${text}`);
        }

        const data = await response.json();
        // debug - list everything in the response
        console.log('Response data:', data);

        let shortUrl = null;
        if (data.shortenedUrl) {
            shortUrl = data.shortenedUrl;
        }
        else {
            console.log('No "shortenedUrl" field in response');
            throw new Error('No shortened Url');
        }

        if (!shortUrl) {
            resultDiv.innerHTML = `<div class="error">Unexpected response format: ${JSON.stringify(data)}</div>`;
            return;
        }

        const a = document.createElement('a');
        a.href = shortUrl;
        a.textContent = shortUrl;
        a.target = '_blank';

        resultDiv.appendChild(document.createTextNode('Shortened URL: '));
        resultDiv.appendChild(a);
    } catch (error) {
        console.error(error);
        resultDiv.innerHTML = `<div class="error">Request failed: ${error.message}</div>`;
    }
});


const gotoBtn = document.getElementById('goto-btn');
const codeInput = document.getElementById('code-input');
gotoBtn.addEventListener('click', () => {
    const code = codeInput.value.trim();
    if (!code) return;
    window.location.href = '/' + encodeURIComponent(code);
});
