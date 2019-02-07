from random import randint
from time import sleep
from urllib.request import Request, urlopen

prefix="https://www.senate.gov/legislative/LIS/roll_call_votes/vote1152/vote_115_2_"

for i in range(52, 275):
	url = prefix + "{:05d}".format(i) + ".xml"
	print(url)
	
	request = Request(url)
	request.add_header('User-Agent', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36')
	connection = urlopen(request)
	xmlcontents = connection.read()

	outfilename = "vote" + str(i) + ".xml"

	print(outfilename)
	#sleep(randint(1,5))
	outfile = open(outfilename, "wb")
	outfile.write(xmlcontents)
	outfile.close()
