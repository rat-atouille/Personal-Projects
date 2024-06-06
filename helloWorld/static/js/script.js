const url = 'https://openlibrary.org/search.json';
var input = document.getElementById('input-box');
var searchContainer = document.querySelector('.search-result');
var loadingSpinner = document.querySelector('.loading-spinner'); 


const default_page = 0;
const default_wishlist = false;
var shelf = [];

toggleListContainerVisibility();

document.addEventListener('DOMContentLoaded', function() {
    loadShelf();
    show();
});

// Function to toggle visibility of list container
function toggleListContainerVisibility() {
    if (searchContainer.children.length === 0) {
        searchContainer.style.display = 'none';
    } else {
        searchContainer.style.display = 'block';
    }
}


function clearSearch() {
    while (searchContainer.firstChild) {
        searchContainer.removeChild(searchContainer.firstChild);
    }
    toggleListContainerVisibility();
}

function search() {
    if (searchContainer.children.length != 0) {
        clearSearch();
    }
    if (input.value === '' || input.value === null) {
        alert("Null Search");
    } else {
        console.log(input.value);
        var temp = (input.value).replace(" ", "+");
        apiSearch(url + '?title=' + temp);
    }
}

function apiSearch(search) {
    // Show loading spinner
    loadingSpinner.style.display = 'block';

    fetch(search)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (data && data.numFound === 0) { // Check if data exists before accessing numFound
                notFound();
            } else {
                var array = data.docs;
                display(array);
            }
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        })
        .finally(() => {
            // Hide loading spinner after API call is complete
            loadingSpinner.style.display = 'none';
        });
}

function display(data) {
    console.log(data[0]);

    for (var i = 0; i < data.length; i++) {
        add(data[i]);
    }
    toggleListContainerVisibility();
}

function notFound() {
    alert("Book doesn't exist.");
}

function add(book) {
    var li = document.createElement("li");

    var title = document.createElement('div');
    title.classList.add('title');
    title.innerHTML = book.title;

    var author = document.createElement('div');
    author.classList.add('author');
    author.innerHTML = book.author_name;

    var button = document.createElement("div");
    var addButton = document.createElement("button");
    addButton.classList.add('add');
    addButton.innerHTML = "Add";
    button.appendChild(addButton);
    addButton.addEventListener('click', addToShelf); 

    var cover = document.createElement("img");
    cover.classList.add('cover');
    cover.src = book.cover_i != null ? "https://covers.openlibrary.org/b/id/" + book.cover_i + "-M.jpg" : "images/no_cover.png";

    li.appendChild(cover);
    li.appendChild(title);
    li.appendChild(author);
    li.appendChild(button);

    var searchContainer = document.querySelector('.search-result');
    searchContainer.appendChild(li);
    console.log(li);
}

//search by pressing enter key
function searchKey(event) {
    if (event.key === 'Enter' || event.keyCode === 13) {
        console.log("entered");
        search();
    }
}

// add the book to the list
function addToShelf(event) {
    var selected = event.target.closest('li');
    if (!selected) {
        console.error('No parent li element found.');
        return;
    }

    var selected_title = selected.querySelector('.title').innerText;
    var selected_author = selected.querySelector('.author').innerText;
    var selected_cover_src = selected.querySelector('.cover').src;

    var book = {
        cover: selected_cover_src,
        title: selected_title,
        author: selected_author,
        pages: default_page,
        rating: default_wishlist
    };

    shelf.push(book);
    saveShelf();
    alert("Added <<" + selected_title + ">> to your shelf");
}

function saveShelf() {
    localStorage.setItem('shelf', JSON.stringify(shelf));
}

function loadShelf() {
    const shelfData = localStorage.getItem('shelf');
    if (shelfData) {
        shelf = JSON.parse(shelfData);
    }
}

function clear() {
    localStorage.clear();
}

// Save data
function save() {
    localStorage.setItem("data", searchContainer.innerHTML);
}

// Display data
function show() {
    searchContainer.innerHTML = localStorage.getItem("data");
}
show();
