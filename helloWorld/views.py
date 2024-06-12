from django.shortcuts import render, redirect
from django.contrib import messages
from django.contrib.auth.models import User
from helloWorld.models import Profile

# Create your views here.

# open main page
def index(request):
    return render(request, "web/main.html")

# open shelf page
def shelf(request):
    return render(request, "web/shelf.html")

# open login page
def login(request):
    return render(request, "web/login.html")

def signup(request):
    if request.method == 'POST':
        name = request.POST['uname']
        username = request.POST['uid']
        email = request.POST['uemail']
        password1 = request.POST['psw1']
        password2 = request.POST['psw2']

        if password1 == password2:
            if User.objects.filter(email=email).exists():
                messages.info(request, "Email already exists")
                return redirect('signup')
            elif User.objects.filter(username=username).exists():
                messages.info(request, 'Username already exists')
                return redirect('signup')
            else:
                user = User.objects.create_user(username=username, email=email, password=password1)
                user.first_name = name
                user.save()
                new_profile = Profile.objects.create(user=user)
                new_profile.save()
                return redirect('login')
        else:
            messages.info(request, 'Passwords do not match')
            return redirect('signup')
    return render(request, "web/signup.html")
