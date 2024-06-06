from django.http import HttpResponse
from django.shortcuts import render

# Create your views here.

# open main page
def index(request):
 #if "tasks" not in request.session:
  # request.session["tasks"]=[]
 return render(request, "web/main.html")

# open shelf page
def shelf(request):
  return render(request, "web/shelf.html")

# open login 
def login(request):
  return render(request, "web/login.html")