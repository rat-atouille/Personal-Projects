
const default_page = 0;
const default_wishlist = false;
var shelfContainer = document.querySelector('.shelf_container');
var shelf = [];
var selected;
var selectedIndex;

var colors = [ "#c2c367", "#627425", "#354d0f", "#678791", "#fff0db"];

document.addEventListener('DOMContentLoaded', function() {
  loadShelf();
  shelfDisplay();
  rowDisplay();
});

function rowDisplay(){
  const rows = document.querySelectorAll('.row');

  rows.forEach(row => {
    if (!row.children.length) {
      row.style.minHeight = '100px';
    } else {
      row.style.minHeight = ''; // Reset if items are present
    }
  });
}

function shelfDisplay() {
  const shelfContainer = document.querySelector('.row');

  for (var i = 0; i < shelf.length; i++) {
      console.log(shelf[i]);
      var li = document.createElement("li");

      var trash = document.createElement('span');
      trash.innerHTML = "\u00d7";
      trash.classList.add('trash');
      trash.addEventListener('click', deleteBook);

      var title = document.createElement('div');
      title.classList.add('title');
      title.innerHTML = shelf[i].title;

      var author = document.createElement('div');
      author.classList.add('author');
      author.innerHTML = shelf[i].author;
      
      var bookmark = document.createElement('span');
      bookmark.classList.add('wishlist');
      bookmark.innerHTML = ".";
      bookmark.style.background = shelf[i].rating ? "red" : "transparent";
      bookmark.addEventListener('click', checkWishlist);

      // random color
      var index = Math.floor(Math.random() * 5); 
      li.style.background = colors[index];

      if (index != 4) {
        li.style.color = "#ffffff";
      }
      else {
        trash.style.color = "#000000"
      }

      if (title.innerText.length < 9) {
        li.style.height = "90px";
      } else if (title.innerText.length < 14) {
        li.style.height = "120px";
        bookmark.style.height = "25px";
      }else if (title.innerText.length < 25) {
        li.style.height = "160px";
        bookmark.style.height = "25px";        
      } else {
        bookmark.style.fontSize = "9px";
        bookmark.style.height = "30px";        
      }

      li.appendChild(bookmark);
      li.appendChild(trash);
      li.appendChild(title);
      li.appendChild(author);

      shelfContainer.appendChild(li);
  }
}

function checkWishlist(event) {
  selected = event.target.closest('li');
  selectedIndex = (Array.from(selected.parentElement.children).indexOf(selected));
  var changeStar = shelf[selectedIndex].rating;
  if (changeStar == false) {
    selected.querySelector("span").style.background = "red";
    shelf[selectedIndex].rating = true;
  } else {
    selected.querySelector("span").style.background = "transparent";
    shelf[selectedIndex].rating = false;
  }
  saveShelf();
}

function deleteBook(event) {
  selected = event.target.closest('li');
  selectedIndex = (Array.from(selected.parentElement.children).indexOf(selected));
  if (selectedIndex !== -1) {
    shelf.splice(selectedIndex, 1); 
    selected.remove(); 
    saveShelf();
  }
}

function saveShelf(){
  localStorage.setItem('shelf', JSON.stringify(shelf));
}

function loadShelf() {
  const shelfData = localStorage.getItem('shelf');
  if (shelfData) {
    shelf = JSON.parse(shelfData);
  }
}