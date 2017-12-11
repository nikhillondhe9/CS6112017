import re

regexp = re.compile(("([a-z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+\/=?^_`"
                    "{|}~-]+)*(@|\sat\s)(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?(\.|"
                     "\sdot\s))+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)"))


text_file = open("emails.txt")
text = text_file.read().lower()
text_list = text.split()
for elements in text_list:
    if regexp.search(elements):
        print(elements)

