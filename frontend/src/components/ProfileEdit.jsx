const ProfileEdit = () => {

    return (
        <div className={"min-h-screen flex justify-center pt-24 bg-transparent"}>
            <div className={"bg-light w-[1091px] h-[822px] rounded"}>
                <section className={"flex flex-row justify-between mx-10 mt-10"}>
                    <p className={"text-black heading1-bold content-center"}>Edit profile</p>
                    <img
                        src="/public/profile_picture.png"
                        alt="profile picture"
                        className="w-[120px] h-[120px] rounded-full"
                    />
                </section>
                <section className={"flex flex-row justify-between ms-10 me-20 mt-10"}>
                    <form>
                        <label className={"text-black heading3-bold"}>First name</label><br/>
                        <input type="text" id="name" placeholder="Uin"
                               className="text-black heading3 w-[405px] h-[70px] rounded border border-black ps-5"/>

                    </form>
                    <form>
                        <label className={"text-black heading3-bold"}>Last name</label><br/>
                        <input type="text" id="surname" placeholder="Tglešić"
                               className="text-black heading3 w-[405px] h-[70px] rounded border border-black ps-5"/>
                    </form>
                </section>
                <form className={"ps-10 pt-5"}>
                    <label className={"text-black heading3-bold"}>Email</label><br/>
                    <input type="text" id="email" placeholder="uintglesic@gmail.com"
                           className="text-black heading3 w-[970px] h-[70px] rounded border border-black ps-5"/>
                </form>
                <form className={"ps-10 pt-5"}>
                    <label className={"text-black heading3-bold"}>Username</label><br/>
                    <input type="text" id="username" placeholder="tinjara"
                           className="text-black heading3 w-[970px] h-[70px] rounded border border-black ps-5"/>
                </form>
                <section className={"ps-10 pt-10"}>
                    <button
                        className={"text-orange heading3-bold w-[160px] h-[45px] border-4 border-orange rounded bg-white me-5"}>Cancel
                    </button>
                    <button className={"text-white heading3-bold w-[160px] h-[45px] bg-orange rounded"}>Save</button>
                </section>
            </div>
        </div>
    )
}

export default ProfileEdit;